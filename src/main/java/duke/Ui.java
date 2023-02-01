package duke;

public class Ui {
    final static String DIVIDER =
            "=======================";
    private String encloseWithDivider(String str) {
        return String.format("%s\n%s\n%s%n", DIVIDER, str, DIVIDER);
    }

    /**
     * Shows normal display message.
     * @param outputMsg The message to be displayed.
     */
    public String showNormalMessage(String outputMsg) {
        return encloseWithDivider(outputMsg.trim());
    }

    /**
     * Shows an error message.
     * @param errorMsg The error message to be displayed.
     */
    public String showErrorMessage(String errorMsg) {
        return encloseWithDivider("OOPS!!! " + errorMsg.trim());
    }
}
