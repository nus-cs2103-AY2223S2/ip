package kira.ui;

/**
 * Printer handles the prettifying of the output messages.
 */
public class Printer {

    /**
     * Formats the text and prints.
     *
     * @param raw text to be prettified
     */
    private static void printFormatString(String raw) {
        StringBuilder response =
                new StringBuilder("============= KiraBot ============= \n")
                .append(raw)
                .append("=================================== \n");
        System.out.println(response.toString());
    }

    /**
     * Attaches and prints the error message to the user.
     *
     * @param msg
     */
    protected static void printFormatErrorMsg(String msg) {
        StringBuilder response =
                new StringBuilder("There seems to be a problem...\n")
                .append(msg)
                .append("\n");
        printFormatString(response.toString());
    }

    /**
     * Prints a reply to the commands of the user.
     *
     * @param msg
     */
    protected static void printFormatReplyMsg(String msg) {
        printFormatString(msg + "\n");
    }

}
