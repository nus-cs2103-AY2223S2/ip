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
        String lineBreak = "***---***---***---***---***---***---***" + "\n" + "    ";

        while (sc.hasNext()) {
            String firstWord = sc.next();
            if (firstWord.equals("bye")) {
                System.out.println(lineBreak + "GoodBye, have a nice day!");
                break;
            } else if (firstWord.equals("list")) {
                System.out.println(lineBreak);
                for (Task t : tasks) {
                    System.out.println("    " + t);
                }
            } else if (firstWord.equals("mark")) {
                int ind = Integer.parseInt(sc.next());
                if (ind > tasks.size()) {
                    System.out.println(lineBreak + "This task does not exist");
                } else {
                    Task updatedTask = tasks.get(ind - 1).mark();
                    tasks.set(ind - 1, updatedTask);
                    System.out.println(lineBreak + "Congrats on completing the following task:\n    " + updatedTask);
                }
            } else if (firstWord.equals("unmark")) {
                int ind = Integer.parseInt(sc.next());
                if (ind > tasks.size()) {
                    System.out.println(lineBreak + "This task does not exist");
                } else {
                    Task updatedTask = tasks.get(ind - 1).unmark();
                    tasks.set(ind - 1, updatedTask);
                    System.out.println(lineBreak + "Unchecked the following task:\n    " + updatedTask);
                }
            }
            else if (firstWord.equals("deadline")) {
                index++;
                String remaining = sc.nextLine();
                String[] parts = remaining.split("/");
                Deadline task = new Deadline(index, parts[0], false, parts[1]);
                tasks.add(task);
                System.out.println(lineBreak + "Successfully added the following task:\n    " + task);
            }

            else if (firstWord.equals("event")) {
                index++;
                String remaining = sc.nextLine();
                String[] parts = remaining.split("/");
                Event task = new Event(index, parts[0], false, parts[1], parts[2]);
                tasks.add(task);
                System.out.println(lineBreak + "Successfully added the following task:\n    " + task);
            }

            else if (firstWord.equals("todo")) {
                index++;
                String remaining = sc.nextLine();
                Todo task = new Todo(index, remaining, false);
                tasks.add(task);
                System.out.println(lineBreak + "Successfully added the following task:\n    " + task);
            }
        }
    }
}

class Task {
    protected final int number;
    protected final String name;
    protected final String status;

    public Task(int number, String name, boolean status) {
        this.number = number;
        this.name = name;
        if (status) {
            this.status = "[X]";
        } else {
            this.status = "[ ]";
        }
    }

    public String toString() {
        return number + "." + status + " " + name;
    }

    public Task mark() {
        return new Task(number, name, true);
    }

    public Task unmark() {
        return new Task(number, name, false);
    }
}

class Deadline extends Task {
    protected final String deadline;

    Deadline (int number, String name, boolean status, String deadline) {
        super(number, name, status);
        this.deadline = deadline;
    }

    public String toString() {
        return number + ".[D]" + status + " " + name + "(" + deadline + ")";
    }

    public Deadline mark() {
        return new Deadline(number, name, true, deadline);
    }

    public Deadline unmark() {
        return new Deadline(number, name, false, deadline);
    }
}

class Event extends Task {
    protected final String from;
    protected final String to;

    Event(int number, String name, boolean status, String from, String to) {
        super(number, name, status);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return number + ".[E]" + status + " " + name + "(" + from + to + ")";
    }

    public Event mark() {
        return new Event(number, name, true, from, to);
    }

    public Event unmark() {
        return new Event(number, name, false, from, to);
    }
}

class Todo extends Task {
    Todo(int number, String name, boolean status) {
        super(number, name, status);
    }

    public String toString() {
        return number + ".[T]" + status + " " + name;
    }

    public Todo mark() {
        return new Todo(number, name, true);
    }

    public Todo unmark() {
        return new Todo(number, name, false);
    }
}










