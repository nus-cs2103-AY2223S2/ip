import java.util.*;

public class Duke {

    /**
     * method to print messages in desired format
     * @param message a string describing the message
     */
    static void printToFormat(String message) {
        String lineBreak1 = "(((***---***---***---***---***---***\n";
        String lineBreak2 = "\n---***---***---***---***---***---***)))\n\n";
        System.out.println(lineBreak1 + message + lineBreak2);
    }
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while (sc.hasNext()) {
            try {
                /**
                 * @param inputLine: a String that is the command entered by the user
                 * @param words[]: an array whose elements are from inputline separated by
                 * a space. used to determine which command is entered
                 */
                String inputLine = sc.nextLine();
                DukeException.checkInput(inputLine);
                String words[] = inputLine.split(" ");
                Command command = Command.valueOf(words[0].toUpperCase());
                switch (command) {
                    case BYE: 
                        printToFormat("    GoodBye, have a nice day!");
                        break;
                    case LIST:
                    /**
                     * loop through all tasks in the arraylist and print out each task
                     */
                        StringBuilder sb = new StringBuilder();
                        for (int i = 1; i <= tasks.size(); i++) {
                            sb.append("    " + i + ". " + tasks.get(i - 1) + "\n");
                        }
                        printToFormat(sb.toString());
                        break;
                    case MARK:
                    /**
                     * change the specified task's status to "[X]"
                     */
                        int ind = Integer.parseInt(words[1]);
                    /**
                     * prints error message if the index is too large
                     */
                        if (ind > tasks.size()) {
                            printToFormat("    This task does not exist");
                        } else {
                            Task updatedTask = tasks.get(ind - 1);
                            tasks.get(ind - 1).mark();
                            printToFormat("    Congrats on completing the following task:\n    " + updatedTask);
                        }
                        break;
                    case UNMARK:
                    /**
                     * changed the specified task's staus to "[ ]"
                     */
                        int ind1 = Integer.parseInt(words[1]);
                        if (ind1 > tasks.size()) {
                            printToFormat("    This task does not exist");
                        } else {
                            Task updatedTask = tasks.get(ind1 - 1);
                            tasks.get(ind1 - 1).unmark();
                            printToFormat("    Unchecked the following task:\n    " + updatedTask);
                        }
                        break;
                    case DEADLINE:
                    /**
                     * creates and adds a deadline task to the arraylist of all tasks
                     */
                        String[] parts = inputLine.split("/");
                        Deadline task = new Deadline(parts[0].split(" ")[1], false, parts[1]);
                        tasks.add(task);
                        printToFormat("    Successfully added the following task:\n    " + task);
                        break;
                    case EVENT:
                    /**
                     * creates and adds an event task to the arraylist of all tasks
                     */
                        String[] parts1 = inputLine.split("/");
                        Event event = new Event(parts1[0].split(" ")[1], false, parts1[1], parts1[2]);
                        tasks.add(event);
                        printToFormat("    Successfully added the following task:\n    " + event);
                        break;
                    case TODO:
                    /**
                     * creates and adds a todo task to the arraylist of all tasks
                     */
                        Todo todo = new Todo(inputLine.split(" ", 2)[1], false);
                        tasks.add(todo);
                        printToFormat("    Successfully added the following task:\n    " + todo);
                        break;
                    case DELETE:
                    /**
                     * removes the task at the specified index
                     */
                        printToFormat("    The following task is removed:\n    " + tasks.remove(Integer.parseInt(words[1]) - 1));
                        break;
                    }
            } catch (DukeException e) {
                /**
                 * prints out the error message if an error is caught
                 */
                printToFormat("    " + e.getMessage());
            }
        }
    }
}

/**
 * Creates a Task class to handle different tasks
 */

class Task {
    /**
     * @param name: a string indicating the name of the task
     * @param status: a boolean indicating whether the task is done or not
     */
    protected String name;
    protected String status;

    public Task(String name, boolean status) {
        this.name = name;
        if (status) {
            this.status = "[X]";
        } else {
            this.status = "[ ]";
        }
    }
    /**
     * overrides the toString method
     */
    public String toString() {
        return "." + status + " " + name;
    }
    /**
     * method to update a task as done.
     * @return a new Task with status being true
     */
    public void mark() {
        this.status = "[X]";
    }
    /**
     * method to update a task as undone
     * @return a new Task with status being false
     */
    public void unmark() {
        this.status = "[ ]";
    }
}
/**
 * Creates a Deadline class that inherits from Task
 * to handle deadline tasks
 */
class Deadline extends Task {
    protected final String deadline;
    /**
     * @param name: a string indicating the name of the task
     * @param status: a String indicating whether the task is done or not
     * @param deadline: a string indicating the deadline of the task
     */
    Deadline (String name, boolean status, String deadline) {
        super(name, status);
        this.deadline = deadline;
    }
    /**
     * overrides the toString method
     */
    public String toString() {
        return "[D]" + status + " " + name + "(" + deadline + ")";
    }
}

class Event extends Task {
    protected final String from;
    protected final String to;
    /**
     * 
     * @param name: a string indicating thename of the Event task
     * @param status: a String that checks if the Event is done or not
     * @param from: a string representing the starting time passed in by the user
     * @param to: a string representing the ending time passed in by the user
     */
    Event(String name, boolean status, String from, String to) {
        super(name, status);
        this.from = from;
        this.to = to;
    }
    /**
     * overrrides toString method
     */
    public String toString() {
        return "[E]" + status + " " + name + "(" + from + to + ")";
    }
}

class Todo extends Task {
    /**
     * 
     * @param name: a String indicating the name of the todo task
     * @param status: a String indicating whether the task is done
     */
    Todo(String name, boolean status) {
        super(name, status);
    }
    /**
     * overrides toString method
     */
    public String toString() {
        return "[T]" + status + " " + name;
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /**
     * 
     * @param inputLine: a line of command entered by the user, to be checked if it is valid
     * @throws DukeException: throws a customised exception message if the command input is 
     * not valid.
     */
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

/**
 * a list of valid Commands as enum
 */
enum Command {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
}