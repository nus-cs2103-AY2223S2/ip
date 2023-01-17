
// import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Duke {
    private static final String LINE_STRING = "____________________________________________________________";
    private static final String WELCOME_STRING = "Hello! I'm Duke\n      What can I do for grades?";
    private static final String END_STRING = "Bye. Hope to see you again soon!";
    private static final String EMPTY_LIST_STRING = "Hey, the list is empty!";
    private static final String MARK_DONE_STRING = "Nice! I've marked this task as done\n       ";
    private static final String UNMARK_DONE_STRING = "OK, I've marked this task as not done yet\n       ";
    private static final String NO_INT_ERR_STRING = "Hey, you did not enter any numbers";
    private static final String OUT_RANGE_ERR_STRING = "Hey, the number you've entered is not vaild";
    private static final String UNKNOWN_ERR_STRING = "Hey, an unknown error happended, oh no";
    private static final String EMPTY_ERR_STRING = "Hey, ☹ The description of a todo cannot be empty.";
    private static final String UNKNOWN_CMD_ERR_STRING = "Hey, ☹ I'm sorry, but I don't know what that means :-(";
    private static final String MISSING_ARGS_STRING = "Hey, ☹ I'm sorry, but you are missing some arguments";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ / _   _| | _____ \n"
                + "| | | | | | | |/ / _ /\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ /__,_|_|/_/___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);

        printer(WELCOME_STRING);

        ArrayList<Task> tasksList = new ArrayList<Task>(100);

        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                boolean end = false;
                switch (input) {
                    case "list":
                        if (tasksList.size() == 0) {
                            printer(EMPTY_LIST_STRING);
                        } else {
                            String toPrint = "";
                            for (int i = 0; i < tasksList.size(); i++) {
                                toPrint += ((i + 1) + "." + tasksList.get(i)) + "\n      ";
                            }
                            printer(toPrint.substring(0, toPrint.length() - 7));
                        }
                        break;
                    case "bye":
                        end = true;
                        printer(END_STRING);
                        break;
                    case "exit":
                        end = true;
                        printer(END_STRING);
                        break;
                    default:
                        // Have to do it at the starts with because what if "todo mark this as done"
                        if (input.startsWith("mark") || input.startsWith("unmark")) {
                            int taskNo = getNumbers(input) - 1;
                            if (input.startsWith("unmark")) {
                                tasksList.get(taskNo).unmarkAsDone();
                                printer(UNMARK_DONE_STRING + tasksList.get(taskNo));
                            } else {
                                tasksList.get(taskNo).markAsDone();
                                printer(MARK_DONE_STRING + tasksList.get(taskNo));
                            }
                            break;
                        } else if (input.startsWith("todo ") || input.startsWith("todo")) {
                            String title = input.substring("todo".length());
                            if (input.startsWith("todo ")) {
                                input.substring("todo ".length());
                            }
                            if (title.length() == 0) {
                                throw new DukeException(EMPTY_ERR_STRING);
                            }
                            Task newTask = new Todo(title);
                            tasksList.add(newTask);
                            printer("added: " + newTask);
                        } else if (input.startsWith("deadline ") || input.startsWith("deadline")) {
                            if (input.indexOf("/by") == -1) {
                                throw new DukeException(MISSING_ARGS_STRING);
                            }
                            String title = input.substring("deadline".length(), input.indexOf("/by"));
                            if (input.startsWith("deadline ")) {
                                input.substring("deadline ".length(), input.indexOf("/by"));
                            }
                            if (title.length() == 0) {
                                throw new DukeException(EMPTY_ERR_STRING);
                            }
                            Task newTask = new Deadline(title,
                                    input.substring(input.indexOf("/by")).replace("/by ", ""));
                            tasksList.add(newTask);
                            printer("added: " + newTask);
                        } else if (input.startsWith("event ") || input.startsWith("event")) {
                            if (input.indexOf("/from") == -1 || input.indexOf("/to") == -1) {
                                throw new DukeException(MISSING_ARGS_STRING);
                            }
                            String title = input.substring("event".length(), input.indexOf("/from"));
                            if (input.startsWith("event ")) {
                                input.substring("event ".length(), input.indexOf("/from"));
                            }
                            if (title.length() == 0) {
                                throw new DukeException(EMPTY_ERR_STRING);
                            }
                            Task newTask = new Event(title,
                                    input.substring(input.indexOf("/from"), input.indexOf("/to")).replace("/from ", ""),
                                    input.substring(input.indexOf("/to")).replace("/to ", ""));
                            tasksList.add(newTask);
                            printer("added: " + newTask);
                        } else {
                            throw new DukeException(UNKNOWN_CMD_ERR_STRING);
                        }
                        break;
                }
                if (end) {
                    break;
                }
            } catch (Exception e) {
                // System.out.println(e);
                if (e instanceof IndexOutOfBoundsException) {
                    printer(OUT_RANGE_ERR_STRING);
                } else if (e instanceof DukeException) {
                    printer(e.getMessage());
                } else {
                    printer(UNKNOWN_ERR_STRING);
                }
            }
        }
        sc.close();
    }

    private static void printer(String toPrint) {
        System.out.println("    " + LINE_STRING);
        System.out.println("      " + toPrint);
        System.out.println("    " + LINE_STRING);
        System.out.println();
    }

    private static int getNumbers(String input) throws DukeException {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String numberString = matcher.group();
            int number = Integer.parseInt(numberString);
            return number;
        } else {
            throw new DukeException(NO_INT_ERR_STRING);
        }
    }
}

abstract class Task {
    private String title;
    private boolean done = false;

    Task(String title) {
        this.title = title;
    }

    void markAsDone() {
        this.done = true;
    }

    void unmarkAsDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String returnString = "";
        if (done) {
            returnString = "[X] ";
        } else {
            returnString = "[ ] ";
        }
        return returnString + title;
    }

}

class Todo extends Task {
    Todo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    Deadline(String title, String by) {
        super(title);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    Event(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

class DukeException extends Exception {
    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }

    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DukeException(Throwable cause) {
        super(cause);
    }
}
