package duke.ui;

/**
 * Class mainly used for displaying and formatting messages.
 */
public class Ui {
    private static String welcomeMessage = "Hello from ClashPlanner\n";
    public static String getWelcomeMessage() {
        return welcomeMessage;
    }
    /**
     * Formats all non-error messages for output
     * @param message a String to be displayed
     */
    public String changeToFormat(String message) {
        String lineBreak1 = "-->-->-->-->-->-->-->-->-->-->-->\n    ";
        String lineBreak2 = "\n<--<--<--<--<--<--<--<--<--<--\n\n";
        return (lineBreak1 + message + lineBreak2);
    }
}
