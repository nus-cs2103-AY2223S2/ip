package duke.command;

import duke.task.*;

public class Ui {
    public Ui(){}

    /**
     * Shows logo and welcome.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\nHello, I'm Duke");
        System.out.println("What can I do for you?");
    }


    /**
     * Prints out error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints out loading error message.
     */
    public void showLoadingError() {
        System.out.println("File not existed");
    }

    /**
     * Prints out line for separation.
     */
    public void showLine() {
        System.out.println("_____________________________");
    }
}