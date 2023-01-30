package duke;

/**
 * The Ui class encapsulates interactions with
 * the user
 */

public class Ui {
    private static String logo = " ____        _        \n"
    + "|  _ \\ _   _| | _____ \n"
    + "| | | | | | | |/ / _ \\\n"
    + "| |_| | |_| |   <  __/\n"
    + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints the welcome message whenever
     * the program is started
     */
    public static void showWelcomeMessage() {
        String welcomeMsg = "Hello from\n" + logo +
                "\n" + "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(welcomeMsg);
    }

    /**
     * Prints the goodbye message whenever
     * the program is terminated.
     */
    public static void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message to show to the user
     *
     * @param message The string to be printed
     */
    public static void showToUser(String message) {
        System.out.println(message);
    }
}
