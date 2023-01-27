package duke.views;

/**
 * Represents the duke.views of the program. Contains methods for printing to console.
 */
public class UI {
    private static final String DATE_FORMAT = "YYYY-MM-DD";

    public static String separatorLine() {

        return "\n--------------------------------------------------------------------------------------";
    }

    public static String indentMessage(String message) {
        return "\n\t" + message;
    }

    public static String newLine() {
        return "\n";
    }

    /**
     * Welcome message on start.
     */
    public static String welcomeMessage() {
        return "Hello! I'm duke.Duke. What can i do for you?";
    }

    public static String helpMessage() {
        return "These are the available commands:"
                + indentMessage("bye")
                + indentMessage("find     [.*]")
                + indentMessage("delete   [index]")
                + indentMessage("mark     [index]")
                + indentMessage("unmark   [index]")
                + indentMessage("todo     [description]")
                + indentMessage("date     [" + DATE_FORMAT + "]")
                + indentMessage("list     (todo | deadline | event)?")
                + indentMessage("deadline [description] /by   [" + DATE_FORMAT + "]")
                + indentMessage("event    [description] /from [" + DATE_FORMAT + "] /to [" + DATE_FORMAT + "]")
                + separatorLine();
    }
}
