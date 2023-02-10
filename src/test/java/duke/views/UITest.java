package duke.views;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UITest {
    private static final String DATE_FORMAT = "YYYY-MM-DD";
    private static final String INDENT_SPACES = "    ";

    public static String separatorLine() {
        return "\n--------------------------------------------------------------------";
    }

    public static String indentMessage(String message) {
        return "\n" + INDENT_SPACES + message;
    }

    @Test
    public void welcomeMessage_printMessage_correctStringReturned() {
        String message = UI.welcomeMessage();
        assertEquals("Hello! I'm Duke. What can i do for you? "
                + "You can click on the help button on the top right to view the available commands!", message);
    }

    @Test
    public void helpMessage_printMessage_correctStringReturned() {
        String message = UI.helpMessage();
        assertEquals("These are the available commands:"
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
                + separatorLine(), message);
    }

}
