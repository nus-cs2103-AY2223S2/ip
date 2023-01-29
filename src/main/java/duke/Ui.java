package duke;

import java.util.Scanner;

/**
 * Interacts with user.
 */
public class Ui {
    /**
     * Prints custom Duke chatbot logo and welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads user's commands and returns it as a string to be read by Parser object.
     * @return User's command in string.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        return str;
    }

    /**
     * Prints divider line to separate user commands and Duke's replies.
     */
    public void showLine() {
        System.out.println("__________________________________________________");
    }

    /**
     * Prints error message when Duke chatbot runs into errors while loading past data.
     */
    public void showLoadingError() {
        System.out.println("Oops!! There was a problem loading past data. Try rebooting me again!");
    }

    /**
     * Prepends Duke chatbot's custom reply to errors to the error message.
     * @param message Error message from error encountered by Duke chatbot.
     * @return Error message in string.
     */
    public String showError(String message) {
        return "Oops!! There was an error" + message;
    }
}
