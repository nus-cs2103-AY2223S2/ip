import java.util.*;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        int index = 0;
        String lineBreak1 = "(((***---***---***---***---***---***\n";
        String lineBreak2 = "---***---***---***---***---***---***)))\n\n";

        while (sc.hasNext()) {
            try {    
                String inputLine = sc.nextLine();
                DukeException.checkInput(inputLine);
                String words[] = inputLine.split(" ");
                Command command = Command.valueOf(words[0].toUpperCase());
                switch (command) {
                    case BYE: 
                        System.out.println(lineBreak1 + "    GoodBye, have a nice day!\n" + lineBreak2);
                        break;
                    case LIST:
                        System.out.print(lineBreak1);
                        for (int i = 1; i <= tasks.size(); i++) {
                            System.out.print("    " + i + ". " + tasks.get(i - 1) + "\n");
                        }
                        System.out.print(lineBreak2);
                        break;
                    case MARK:
                        int ind = Integer.parseInt(words[1]);
                        if (ind > tasks.size()) {
                            System.out.println(lineBreak1 + "This task does not exist");
                        } else {
                            Task updatedTask = tasks.get(ind - 1).mark();
                            tasks.set(ind - 1, updatedTask);
                            System.out.println(lineBreak1 + "Congrats on completing the following task:\n    " + updatedTask);
                            System.out.print(lineBreak2);
                        }
                        break;
                    case UNMARK:
                        int ind1 = Integer.parseInt(words[1]);
                        if (ind1 > tasks.size()) {
                            System.out.println(lineBreak1 + "This task does not exist");
                            System.out.print(lineBreak2);
                        } else {
                            Task updatedTask = tasks.get(ind1 - 1).unmark();
                            tasks.set(ind1 - 1, updatedTask);
                            System.out.println(lineBreak1 + "Unchecked the following task:\n    " + updatedTask);
                            System.out.print(lineBreak2);
                        }
                        break;
                    case DEADLINE:
                        String[] parts = inputLine.split("/");
                        Deadline task = new Deadline(parts[0].split(" ")[1], false, parts[1]);
                        tasks.add(task);
                        System.out.println(lineBreak1 + "Successfully added the following task:\n    " + task);
                        System.out.print(lineBreak2);
                        break;
                    case EVENT:
                        String[] parts1 = inputLine.split("/");
                        Event event = new Event(parts1[0].split(" ")[1], false, parts1[1], parts1[2]);
                        tasks.add(event);
                        System.out.println(lineBreak1 + "Successfully added the following task:\n    " + event);
                        System.out.print(lineBreak2);
                        break;
                    case TODO:
                        
                        Todo todo = new Todo(inputLine.split(" ", 2)[1], false);
                        tasks.add(todo);
                        System.out.println(lineBreak1 + "Successfully added the following task:\n    " + todo);
                        System.out.print(lineBreak2);
                        break;
                    case DELETE:
                        System.out.println(lineBreak1+ "The following task is removed:\n    " + tasks.remove(Integer.parseInt(words[1]) - 1));
                        System.out.print(lineBreak2);
                        break;
                    }
            } catch (DukeException e) {
                System.out.println(lineBreak1 + e.getMessage());
                System.out.print(lineBreak2);
            }
        }
    }
}

class Task {
    protected final String name;
    protected final String status;

    public Task(String name, boolean status) {
        this.name = name;
        if (status) {
            this.status = "[X]";
        } else {
            this.status = "[ ]";
        }
    }

    public String toString() {
        return "." + status + " " + name;
    }

    public Task mark() {
        return new Task(name, true);
    }

    public Task unmark() {
        return new Task(name, false);
    }
}

class Deadline extends Task {
    protected final String deadline;

    Deadline (String name, boolean status, String deadline) {
        super(name, status);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + status + " " + name + "(" + deadline + ")";
    }

    public Deadline mark() {
        return new Deadline(name, true, deadline);
    }

    public Deadline unmark() {
        return new Deadline(name, false, deadline);
    }
}

class Event extends Task {
    protected final String from;
    protected final String to;

    Event(String name, boolean status, String from, String to) {
        super(name, status);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + status + " " + name + "(" + from + to + ")";
    }

    public Event mark() {
        return new Event(name, true, from, to);
    }

    public Event unmark() {
        return new Event(name, false, from, to);
    }
}

class Todo extends Task {
    Todo(String name, boolean status) {
        super(name, status);
    }

    public String toString() {
        return "[T]" + status + " " + name;
    }

    public Todo mark() {
        return new Todo(name, true);
    }

    public Todo unmark() {
        return new Todo(name, false);
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public static void checkInput(String inputLine) throws DukeException {
        if (inputLine.equals("deadline")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (inputLine.equals("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (inputLine.equals("event")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else if (inputLine.equals("delete")) {
            throw new DukeException("☹ OOPS!!! Please specify which task to delete.");
        } 
    }
}

enum Command {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
}










