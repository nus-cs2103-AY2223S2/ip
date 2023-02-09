package duke;

/**
 * Interacts with user.
 */
public class Ui {
    /**
     * Creates custom Duke chatbot logo and welcome message.
     * @return Welcome message.
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String str = "Hello from\n" + logo + "\n";
        str += "Hello! I'm Duke\n";
        str += "What can I do for you?\n";
        return str;
    }

    /**
     * Gives error message when Duke chatbot runs into errors while loading past data.
     * @return Loading error message.
     */
    public String showLoadingError() {
        return "Oops!! There was a problem loading past data. Try rebooting me again!";
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
