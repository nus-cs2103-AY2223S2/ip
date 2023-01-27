import java.util.Scanner;

public class Duke {

    /** String of the Logo */
    static String MSG_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** Message to be printed before to greet the user */
    static String MSG_GREET = "Hello! I'm Duke\n"
            + "What can I do for you?";

    /** Message to be printed before exiting */
    static String MSG_EXIT = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        // Greets the user
        greet();

        // Decision loop
        boolean isContinue_decisionLoop = true;
        while (isContinue_decisionLoop) {
            // Get user's input
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            // Decision Tree
            if (input.equals("bye")) {
                isContinue_decisionLoop = false;
            } else {
                echo(input);
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
}