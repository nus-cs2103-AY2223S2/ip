package duke;

/**
 * Class for managing the UI.
 */
public class Ui {
    /**
     * Shows normal display message.
     * @param outputMsg The message to be displayed.
     */
    public String showNormalMessage(String outputMsg) {
        return outputMsg.trim();
    }

    /**
     * Shows an error message.
     * @param errorMsg The error message to be displayed.
     */
    public String showErrorMessage(String errorMsg) {
        return "OOPS!!! " + errorMsg.trim();
    }
}
