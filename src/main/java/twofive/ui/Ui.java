package twofive.ui;

import java.util.Scanner;

/**
 * Handles interaction with the user by reading inputs from the user
 * and displaying the corresponding outputs.
 */
public class Ui {
    private static final String LOGO = "  _______            ______ _\n"
            + " |__   __|          |  ____(_)\n"
            + "    | |_      _____ | |__   ___   _____\n"
            + "    | \\ \\ /\\ / / _ \\|  __| | \\ \\ / / _ \\\n"
            + "    | |\\ V  V / (_) | |    | |\\ V /  __/\n"
            + "    |_| \\_/\\_/ \\___/|_|    |_| \\_/ \\___|\n";
    private static final String DIVIDER = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user when they run the application.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        this.showLine();
        System.out.println("In case you're still not clear, I'm TwoFive!");
        System.out.println("I'm your personal assistant chatbot");
        System.out.println("What can I do for you?");
        this.showLine();
    }

    /**
     * Reads a command entered by the user.
     *
     * @return String containing the full command
     * entered by the user in the same line.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a given error message.
     *
     * @param error The error message to be displayed.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Displays a divider.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays messages when the user exists the application.
     */
    public void showExit() {
        System.out.println(" Tasks saved successfully.");
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * Displays a given message which is not an error.
     *
     * @param message A message to be displayed which is not an error.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
