import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /** The Logo of Duke */
    private static final String MSG_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** Line to be appended before and after a message */
    private static final String MSG_LINE = "____________________________________________________________\n";

    /** Message to be printed before to greet the user */
    private static final String[] MSG_GREET = {"Hello! I'm Duke", "What can I do for you?"};

    /** Message to be printed before exiting */
    private static final String MSG_EXIT = "Bye. Hope to see you again soon!";

    /** Message to be printed after marking task as done */
    private static final String MSG_MARK = "Nice! I've marked this task as done:";

    /** Message to be printed after changing status of task to not done */
    private static final String MSG_UNMARK = "OK, I've marked this task as not done yet:";

    /** Message to be printed after adding task to list of tasks */
    private static final String[] MSG_ADD = {"Got it. I've added this task:", "Now you have ", " tasks in the list."};

    /** Message to be printed after adding task to list of tasks */
    private static final String MSG_DELETE = "Noted. I've removed this task:";

    /** Indentation level of messages to be printed to console */
    private static final int INDENTATION_LEVEL = 4;

    public static void main(String[] args) {
        // Greets the user
        try {
            greet();
        } catch (DukeException e) {
            echo(e.getMessage());
        }

        // Initialise variables
        ArrayList<Task> tasks = new ArrayList<>();

        // Decision loop
        boolean isContinue_decisionLoop = true;
        while (isContinue_decisionLoop) {
            // Get user's input
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String cmd = input.split(" ", 2)[0];

            try {
                // Decision Tree
                switch (cmd) {
                case "todo":
                    add(tasks, Todo.generate(input));
                    break;
                case "deadline":
                    add(tasks, Deadline.generate(input));
                    break;
                case "event":
                    add(tasks, Event.generate(input));
                    break;
                case "mark":
                    mark(tasks, input);
                    break;
                case "unmark":
                    unmark(tasks, input);
                    break;
                case "delete":
                    delete(tasks, input);
                    break;
                case "list":
                    list(tasks);
                    break;
                case "bye":
                    isContinue_decisionLoop = false;
                    break;
                default:
                    throw new DukeException("Unknown", "Unknown Command");
                }
            } catch (DukeException e) {
                echo(e.getMessage());
            } catch (Exception e) {
                // Catches exceptions whose behaviour has not yet been implemented
                try {
                    String[] errorMessage = DukeException.unimplemented(e);
                    int[] indent = {1, 3, 5};
                    echo(errorMessage, indent);
                } catch (DukeException de) {
                    echo(de.getMessage());
                }
            }
        }

        // Exit message
        exit();
    }

    /**
     * Prints specified message on console.
     * Appends a line before and after message.
     *
     * @param msg Message to be printed on console.
     */
    static void echo(String msg) {
        String indent = indent(INDENTATION_LEVEL);
        System.out.println(indent + MSG_LINE
                + indent + msg + "\n"
                + indent + MSG_LINE);
    }

    /**
     * Prints specified message on console.
     * Appends a line before and after message.
     *
     * @param msg Array of message to be printed on console.
     * @param indentLevel Array representing the indent level of each message.
     * @throws DukeException If length of msg and indentLevel do not match
     */
    static void echo(String[] msg, int[] indentLevel) throws DukeException {
        if (indentLevel.length != msg.length) {
            throw new DukeException("List", "Unbalanced List");
        }
        String indent = indent(INDENTATION_LEVEL);

        // Converts list of messages to be printed on console
        StringBuilder output = new StringBuilder();
        output.append(indent(indentLevel[0]))
                .append(msg[0]);
        for (int i = 1; i < msg.length; i++) {
            output.append("\n")
                    .append(indent)
                    .append(indent(indentLevel[i]))
                    .append(msg[i]);
        }

        echo(output.toString());
    }

    /**
     * Returns indentation of the specified indentation level.
     *
     * @param count Level of indentation.
     * @return Indentation
     */
    static String indent(int count) {
        return " ".repeat(count);
    }

    /**
     * Prints greeting message on console.
     *
     * @throws DukeException If length of MSG_GREET and indentLevel do not match
     */
    static void greet() throws DukeException {
        System.out.println("Hello from\n" + MSG_LOGO);
        int[] indentLevel = {1, 1};
        echo(MSG_GREET, indentLevel);
    }

    /**
     * Prints exiting message on console.
     */
    static void exit() {
        echo(" " + MSG_EXIT);
    }

    /**
     * Adds specified task to specified list of tasks.
     *
     * @param tasks List of tasks to add task to.
     * @param task Task to be added to tasks.
     * @throws DukeException If length of output and indentLevel do not match
     */
    static void add(ArrayList<Task> tasks, Task task) throws DukeException {
        tasks.add(task);
        String[] output = {MSG_ADD[0],
                task.toString(),
                MSG_ADD[1]
                        + tasks.size()
                        + MSG_ADD[2]};
        int[] indentLevel = {1, 3, 1};
        echo(output, indentLevel);
    }

    /**
     * Prints specified list of tasks on console.
     *
     * @param tasks List of tasks to print on console.
     * @throws DukeException If list of tasks is empty
     */
    static void list(ArrayList<Task> tasks) throws DukeException {
        if (tasks.size() < 1) {
            throw new DukeException("List", "Empty List");
        }
        String[] output = new String[tasks.size()];
        int[] indentLevel = new int[tasks.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = i + 1
                    + ". "
                    + tasks.get(i);
            indentLevel[i] = 1;
        }
        echo(output, indentLevel);
    }

    /**
     * Marks specified task as done.
     *
     * @param tasks List of tasks to get specified task from.
     * @param input User's input.
     * @throws DukeException If list of tasks is empty
     * @throws DukeException If index is out of bound
     * @throws DukeException If the specified index is not an integer
     */
    static void mark(ArrayList<Task> tasks, String input) throws DukeException {
        int index = input.trim().indexOf(" ");
        if (index < 0) {
            throw new DukeException("Mark", "Empty Index");
        }
        try {
            index = Integer.parseInt(input
                    .substring(index + 1)) - 1;
            tasks.get(index).mark();
            int[] indentLevel = {1, 3};
            String[] message = {MSG_MARK,
                    tasks.get(index).toString()};
            echo(message, indentLevel);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Mark", "Out of Bound");
        } catch (NumberFormatException e) {
            throw new DukeException("Mark", "Not Integer");
        }
    }

    /**
     * Changes status of specified task back to not done.
     *
     * @param tasks List of tasks to get specified task from.
     * @param input User's input.
     * @throws DukeException If list of tasks is empty
     * @throws DukeException If index is out of bound
     * @throws DukeException If the specified index is not an integer
     */
    static void unmark(ArrayList<Task> tasks, String input) throws DukeException {
        int index = input.trim().indexOf(" ");
        if (index < 0) {
            throw new DukeException("Unmark", "Empty Index");
        }
        try {
            index = Integer.parseInt(input
                    .substring(index + 1)) - 1;
            tasks.get(index).unmark();
            int[] indentLevel = {1, 3};
            String[] message = {MSG_UNMARK,
                    tasks.get(index).toString()};
            echo(message, indentLevel);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unmark", "Out of Bound");
        }  catch (NumberFormatException e) {
            throw new DukeException("Unmark", "Not Integer");
        }
    }

    /**
     * Removes specified task from the specified list of tasks.
     *
     * @param tasks List of tasks to get specified task from.
     * @param input User's input.
     * @throws DukeException If list of tasks is empty
     * @throws DukeException If index is out of bound
     * @throws DukeException If the specified index is not an integer
     */
    static void delete(ArrayList<Task> tasks, String input) throws DukeException {
        int index = input.trim().indexOf(" ");
        if (index < 0) {
            throw new DukeException("Delete", "Empty Index");
        }
        try {
            index = Integer.parseInt(input
                    .substring(index + 1)) - 1;
            Task task = tasks.remove(index);
            int[] indentLevel = {1, 3, 1};
            String[] message = {MSG_DELETE,
                    task.toString(),
                    MSG_ADD[1]
                            + tasks.size()
                            + MSG_ADD[2]};
            echo(message, indentLevel);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Delete", "Out of Bound");
        }  catch (NumberFormatException e) {
            throw new DukeException("Delete", "Not Integer");
        }
    }
}