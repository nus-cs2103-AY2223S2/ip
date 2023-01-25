package duke;

public class Ui {
    final static String DIVIDER =
            "____________________________________________________________";
    private void printWithDivider(String str) {
        System.out.printf("\t%s\n\t%s\n\t%s%n", DIVIDER, str, DIVIDER);
    }

    /**
     * Shows normal display message.
     * @param outputMsg The message to be displayed.
     */
    public void showNormalMessage(String outputMsg) {
        printWithDivider(outputMsg.trim());
    }

    /**
     * Shows an error message.
     * @param errorMsg The error message to be displayed.
     */
    public void showErrorMessage(String errorMsg) {
        printWithDivider("☹ OOPS!!! " + errorMsg.trim());
    }
}
