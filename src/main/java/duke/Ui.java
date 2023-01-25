package duke;

public class Ui {
    final static String DIVIDER =
            "____________________________________________________________";
    private void printWithDivider(String str) {
        System.out.println(String.format("\t%s\n\t%s\n\t%s", DIVIDER, str, DIVIDER));
    }
    public void showNormalMessage(String outputMsg) {
        printWithDivider(outputMsg.trim());
    }
    public void showErrorMessage(String errorMsg) {
        printWithDivider("☹ OOPS!!! " + errorMsg.trim());
    }
}
