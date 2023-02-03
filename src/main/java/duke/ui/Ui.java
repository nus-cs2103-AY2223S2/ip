package duke.ui;

import java.util.Scanner;

/**
 * this class is mainly used for displaying and formatting messages.
 */
public class Ui {
    /**
     * the welcome message to be displayed when the programm starts running
     */
    private static String welcomeMessage = "Hello from ClashPlanner\n";

    public static String getWelcomeMessage() {
        return welcomeMessage;
    }

    /**
     * method to take in user input
     * @return a String that is the same as what the use has input
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    /**
     * a unique way to display all non-error messages
     * @param message a String to be displayed
     */
    public String changeToFormat(String message) {
        String lineBreak1 = "-->-->-->-->-->-->-->-->-->-->-->\n    ";
        String lineBreak2 = "\n<--<--<--<--<--<--<--<--<--<--\n\n";
        return (lineBreak1 + message + lineBreak2);
    }
}
