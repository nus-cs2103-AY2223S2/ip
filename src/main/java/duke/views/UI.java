package duke.views;

/**
 * Represents the views of the program. Contains methods for printing to console.
 */
public class UI {
    /** Font options **/
    public static final String FONT_TYPE = "Courier New";
    public static final Integer FONT_SIZE_H1 = 30;
    public static final Integer FONT_SIZE_P = 15;
    public static final Integer FONT_SIZE_SMALL = 12;

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
     * Returns the welcome message on start.
     *
     * @return Welcome message.
     */
    public static String welcomeMessage() {
        return "Hello! I'm Duke. What can i do for you? "
                + "You can click on the help button on the top right to view the available commands!";
    }

    /**
     * Returns the help message.
     *
     * @return The list of commands available.
     */
    public static String helpMessage() {
        return "These are the available commands:"
                + indentMessage("bye")
                + indentMessage("undo")
                + indentMessage("unmark   [task]")
                + indentMessage("mark     [task]")
                + indentMessage("delete   [task]")
                + indentMessage("find     [filter]?")
                + indentMessage("checkout [version]")
                + indentMessage("todo     [description]")
                + indentMessage("date     [" + DATE_FORMAT + "]")
                + indentMessage("list     (todo | deadline | event)?")
                + indentMessage("deadline [description] /by   [" + DATE_FORMAT + "]")
                + indentMessage("event    [description] /from [" + DATE_FORMAT + "] /to [" + DATE_FORMAT + "]")
                + separatorLine();
    }
}
