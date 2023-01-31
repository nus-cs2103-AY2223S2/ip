package duke.ui;

import java.util.Scanner;

/**
 * this class is mainly used for displaying and formatting messages.
 */
public class Ui {
    /**
     * the welcome message to be displayed when the programm starts running
     */
    private String welcomeMessage = "Hello from\n" + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    /**
     * prints the welcome message
     */
    public void welcome() {
        System.out.println(welcomeMessage);
    }

    /**
     * method that reads user input
     * @return the String input by the user
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    /**
     * Displays an error message if the default tasks cannot be loaded.
     */
    public void showLoadingError() {
        this.printToFormat("Sorry, default tasks could not be loaded, starting a fresh task list");
    }

    /**
     * a unique way to display all non-error messages
     * @param message a String to be displayed
     */
    public void printToFormat(String message) {
        String lineBreak1 = "-->-->-->-->-->-->-->-->-->-->-->\n    ";
        String lineBreak2 = "\n<--<--<--<--<--<--<--<--<--<--\n\n";
        System.out.println(lineBreak1 + message + lineBreak2);
    }
}
