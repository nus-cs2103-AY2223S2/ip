import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /** The Logo of Duke */
    static String MSG_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** Line to be appended before and after a message */
    static String MSG_LINE = "____________________________________________________________\n";

    /** Message to be printed before to greet the user */
    static String[] MSG_GREET = {"Hello! I'm Duke", "What can I do for you?"};

    /** Message to be printed before exiting */
    static String MSG_EXIT = "Bye. Hope to see you again soon!";

    /** Message to be printed after marking task as done */
    static String MSG_MARK = "Nice! I've marked this task as done:";

    /** Message to be printed after changing status of task to not done */
    static String MSG_UNMARK = "OK, I've marked this task as not done yet:";

    /** Message to be printed after adding task to list of tasks */
    static String[] MSG_ADD = {"Got it. I've added this task:", "Now you have ", " tasks in the list."};

    /** Indentation level of messages to be printed to console */
    static int INDENTATION_LEVEL = 4;

    public static void main(String[] args) {
        // Greets the user
        greet();

        // Initialise variables
        ArrayList<Task> tasks = new ArrayList<>();

        // Decision loop
        boolean isContinue_decisionLoop = true;
        while (isContinue_decisionLoop) {
            // Get user's input
            Scanner sc = new Scanner(System.in);
            String[] inputs = sc.nextLine().split(" ", 2);

            // Decision Tree
            switch (inputs[0]) {
            case "todo":
                add(tasks, new Todo(inputs[1]));
                break;
            case "deadline":
                String[] deadlineVariable = inputs[1].split(" /by ", 2);
                add(tasks, new Deadline(deadlineVariable[0], deadlineVariable[1]));
                break;
            case "event":
                String[] eventVariable = inputs[1].split(" /from ", 2);
                String[] eventDuration = eventVariable[1].split(" /to ", 2);
                add(tasks, new Event(eventVariable[0], eventDuration[0], eventDuration[1]));
                break;
            case "mark":
                mark(tasks, inputs[1]);
                break;
            case "unmark":
                unmark(tasks, inputs[1]);
                break;
            case "list":
                list(tasks);
                break;
            case "bye":
                isContinue_decisionLoop = false;
                break;
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
     * @param msg Message to be printed on console.
     * @param indentLevel Number of spaces to indent message.
     */
    static void echo(String msg, int indentLevel) {
        String indent = indent(INDENTATION_LEVEL);
        System.out.println(indent + MSG_LINE
                + indent + indent(indentLevel)
                + msg + "\n"
                + indent + MSG_LINE);
    }

    /**
     * Prints specified message on console.
     * Appends a line before and after message.
     *
     * @param msg Array of message to be printed on console.
     * @param indentLevel Array representing the indent level of each message.
     */
    static void echo(String[] msg, int[] indentLevel) {
        String indent = indent(INDENTATION_LEVEL);
        StringBuilder output = new StringBuilder(indent + MSG_LINE);

        for (int i = 0; i < msg.length; i++) {
            output.append(indent);
            if (i < indentLevel.length) {
                output.append(indent(indentLevel[i]));
            }
            output.append(msg[i])
                    .append("\n");
        }

        output.append(indent).append(MSG_LINE);
        System.out.println(output);
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
     */
    static void greet() {
        System.out.println("Hello from\n" + MSG_LOGO);
        int[] indentLevel = {1, 1};
        echo(MSG_GREET, indentLevel);
    }

    /**
     * Prints exiting message on console.
     */
    static void exit() {
        echo(MSG_EXIT, 1);
    }

    /**
     * Adds specified task to specified list of tasks.
     *
     * @param tasks list of tasks to add task to.
     * @param task task to be added to tasks.
     */
    static void add(ArrayList<Task> tasks, String task) {
        tasks.add(new Task(task));
        echo("added: " + task, 1);
    }

    /**
     * Adds specified task to specified list of tasks.
     *
     * @param tasks list of tasks to add task to.
     * @param task task to be added to tasks.
     */
    static void add(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
        String[] output = {MSG_ADD[0],
                task.toString(),
                MSG_ADD[1] + tasks.size() + MSG_ADD[2]};
        int[] indentLevel = {1, 3, 1};

        echo(output, indentLevel);
    }

    /**
     * Prints specified list of tasks on console.
     *
     * @param tasks list of tasks to print on console.
     */
    static void list(ArrayList<Task> tasks) {
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
     * @param tasks list of tasks to get specified task from.
     * @param index index of task to be marked.
     */
    static void mark(ArrayList<Task> tasks, String index) {
        int i = Integer.parseInt(index) - 1;
        tasks.get(i).mark();
        int[] indentLevel = {1, 3};
        String[] message = {MSG_MARK,
                tasks.get(i).toString()};
        echo(message, indentLevel);
    }

    /**
     * Changes status of specified task back to not done.
     *
     * @param tasks list of tasks to get specified task from.
     * @param index index of task to be changed to not done.
     */
    static void unmark(ArrayList<Task> tasks, String index) {
        int i = Integer.parseInt(index) - 1;
        tasks.get(i).unmark();
        int[] indentLevel = {1, 3};
        String[] message = {MSG_UNMARK,
                tasks.get(i).toString()};
        echo(message, indentLevel);
    }
}