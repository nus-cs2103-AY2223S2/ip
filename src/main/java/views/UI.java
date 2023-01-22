package views;

/**
 * Represents the views of the program. Contains methods for printing to console.
 */
public class UI {
    private final String DATE_FORMAT = "YYYY-MM-DD";

    private String indentMessage(String message) {
        return "\n\t " + message;
    }

    private String separatorLine() {
        return "\n------------------------------------------------";
    }


    /**
     * Welcome message on start.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello! I'm Duke. What can i do for you?");
        System.out.println(
                "These are the available commands:"
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
                        + separatorLine());
    }
}
