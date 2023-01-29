package duke.views;

/**
 * Represents the duke.views of the program. Contains methods for printing to console.
 */
public class UI {
    /** Font options **/
    public static final String FONT_TYPE = "Courier New";
    public static final Integer H1_FONT = 30;
    public static final Integer P_FONT = 15;
    public static final Integer SMALL_FONT = 12;

    private static final String DATE_FORMAT = "YYYY-MM-DD";
    private static final String INDENT_SPACES = "    ";

    public static String separatorLine() {
        return "\n--------------------------------------------------------------------";
    }

    public static String indentMessage(String message) {
        return "\n" + INDENT_SPACES + message;
    }

    public static String newLine() {
        return "\n";
    }

    /**
     * Welcome message on start.
     */
    public static String welcomeMessage() {
        return "Hello! I'm Duke. What can i do for you?\n"
                + "Click on the help button on the top right to view the available commands!";
    }

    public static String helpMessage() {
        return "These are the available commands:"
                + indentMessage("bye")
                + indentMessage("unmark   [task]")
                + indentMessage("mark     [task]")
                + indentMessage("delete   [task]")
                + indentMessage("find     [filter]")
                + indentMessage("todo     [description]")
                + indentMessage("date     [" + DATE_FORMAT + "]")
                + indentMessage("list     (todo | deadline | event)?")
                + indentMessage("deadline [description] /by   [" + DATE_FORMAT + "]")
                + indentMessage("event    [description] /from [" + DATE_FORMAT + "] /to [" + DATE_FORMAT + "]")
                + separatorLine();
    }
}
