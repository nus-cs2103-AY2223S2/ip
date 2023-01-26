package duke;

import java.util.Scanner;

/**
 * The Ui class represents the user interface that handles all interaction with the user.
 */
public class Ui {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING = "Hello there! I am 4RTHUR";
    private static final String INIT_ERR_MSG = "Failed to init!";

    private final IFormatter responseFormatter;
    private final Scanner scanner;

    /**
     * Constructs the user interface that handles all interaction with the user.
     */
    public Ui() {
        this.responseFormatter = new ResponseFormatter();
        scanner = new Scanner(System.in);
    }

    /**
     * Returns true if there is user input. This method can block when waiting for user input.
     *
     * @return true if there is user input to process
     */
    public boolean hasUserInput() {
        return scanner.hasNext();
    }

    /**
     * Returns the next user input line.
     *
     * @return next user input string
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays a greeting to the user.
     */
    public void displayGreeting() {
        System.out.println(responseFormatter.format("\n" + LOGO));
        System.out.println(responseFormatter.format(GREETING));
    }

    /**
     * Displays an initialization error message to the user.
     */
    public void displayInitError() {
        System.out.println(responseFormatter.format(INIT_ERR_MSG));
    }

    /**
     * Displays a response to the user.
     *
     * @param response response to display
     */
    public void displayResponse(String response) {
        System.out.println(responseFormatter.format(response));
    }
}
