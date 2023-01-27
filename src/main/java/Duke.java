import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /** String of the Logo */
    static String MSG_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** Message to be printed before to greet the user */
    static String MSG_GREET = " Hello! I'm Duke\n"
            + " What can I do for you?";

    /** Message to be printed before exiting */
    static String MSG_EXIT = " Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        // Greets the user
        greet();

        // Initialise variables
        ArrayList<String> tasks = new ArrayList<>();

        // Decision loop
        boolean isContinue_decisionLoop = true;
        while (isContinue_decisionLoop) {
            // Get user's input
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            // Decision Tree
            switch (input) {
            case "list":
                list(tasks);
                break;
            case "bye":
                isContinue_decisionLoop = false;
                break;
            default:
                add(input, tasks);
                break;
            }
        }

        // Exit message
        exit();
    }

    /**
     * Prints greeting message on console.
     */
    static void greet() {
        System.out.println("Hello from\n" + MSG_LOGO);
        echo(MSG_GREET);
    }

    /**
     * Prints specified message on console.
     * Appends a line before and after message.
     *
     * @param msg message to be printed on console.
     */
    static void echo(String msg) {
        String line = "____________________________________________________________\n";
        System.out.println(line + msg + "\n"
                + line);
    }

    /**
     * Prints exiting message on console.
     */
    static void exit() {
        echo(MSG_EXIT);
    }

    /**
     * Adds specified task to specified list of tasks.
     *
     * @param task task to be added to tasks.
     * @param tasks list of tasks to add task to.
     */
    static void add(String task, ArrayList<String> tasks) {
        tasks.add(task);
        echo(" added: " + task);
    }

    /**
     * Prints specified list of tasks on console.
     *
     * @param tasks list of tasks to print on console.
     */
    static void list(ArrayList<String> tasks) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(" ")
                    .append(i + 1)
                    .append(". ")
                    .append(tasks.get(i))
                    .append("\n");
        }
        echo(output.toString());
    }
}