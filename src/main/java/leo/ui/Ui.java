package leo.ui;

import leo.task.LeoTaskException;

/**
 * Class that handles the user interface.
 */
public class Ui {
    /**
     * Prints the greeting message.
     */
    public void greetUser() {
        printDivider();
        System.out.println("Hello! I'm Leo");
        System.out.println("What can I do for you?");
        printDivider();
    }

    /**
     * Prints a divider.
     */
    public static void printDivider() {
        for (int i = 0; i < 25; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    /**
     * Prints Leo's response with dividers.
     * @param response
     */

    public static void printResponse(String response) {
        printDivider();
        System.out.println(response);
        printDivider();
    }


    /**
     * Prints custom exceptions (LeoTaskException) thrown by Leo.
     * @param e
     */

    public static void printError(LeoTaskException e) {
        printDivider();
        System.out.println(e.getMessage());
        printDivider();
    }

    /**
     * Prints exit message.
     */
    public void printExit() {
        printDivider();
        System.out.println("It was nice talking, see you soon!");
        printDivider();
    }
}
