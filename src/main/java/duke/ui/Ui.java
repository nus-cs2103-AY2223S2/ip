package duke.ui;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    protected Scanner sc;

    /**
     * Creates a user interface that interacts with the user.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns Duke logo
     *
     * @return Duke logo
     */
    public String getLogoMessage() {
        return "Hello from\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }
    public String getWelcomeMessage() {
        return "Hello! I'm Duke\n"
                + "What can I do for you?";
    }
    public String getFarewellMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String getAddMessage() {
        return "Got it. I've added this task:";
    }
    public String getDeleteMessage() {
        return "Noted. I've removed this task:";
    }
    public String getFindMessage() {
        return "Here are the matching tasks in your list:";
    }
    public String getListMessage() {
        return "Here are the tasks in your list:";
    }
    public String getMarkMessage() {
        return "Nice! I've marked this task as done:";
    }
    public String getUnmarkMessage() {
        return "OK, I've marked this task as not done yet:";
    }
    public String getTasksCountMessage(int i) {
        return "Now you have " + i + " tasks in the list.";
    }

    public String getLoadingErrorMessage() {
        return "There was an error while loading from save file";
    }

    /**
     * Reads the command input by the user
     *
     * @return The full command input by the user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows the divider line ("_______")
     */
    public void showLine() {
        System.out.println(" ".repeat(4)
                + "_".repeat(60));
    }

    /**
     * Shows the specified message
     *
     * @param message Message to be shown
     */
    public void echo(String message) {
        String indent = " ".repeat(5);
        System.out.println(indent
                + message.replace("\n",
                "\n" + indent));
    }

    /**
     * Shows Duke's logo and greets the user
     */
    public void showWelcome() {
        // Shows logo of Duke
        System.out.println(getLogoMessage());

        // Shows user greeting
        showLine();
        echo(getWelcomeMessage());
        showLine();
    }

    // Error messages for exceptions specific to Duke.
    /**
     * Shows the error message of the encountered exception
     *
     * @param errorMessage Error message
     */
    public void showError(String errorMessage) {
        echo("☹ OOPS!!! " + errorMessage + " :-(");
    }
}
