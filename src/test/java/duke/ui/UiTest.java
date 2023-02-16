package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {

    private final Ui ui = new Ui();

    @Test
    public void getLogoMessage() {
        // A unit test for Ui#getLogoMessage
        // test setup
        String message = "Hello from\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // automatically verify the response
        assertEquals(ui.getLogoMessage(),
                message);
    }

    @Test
    public void getWelcomeMessage() {
        // A unit test for Ui#getWelcomeMessage
        // test setup
        String message = "Hello! I'm Jamie 😃\n"
                + "A personality of the Duke project.\n"
                + "What can I do for you?";

        // automatically verify the response
        assertEquals(ui.getWelcomeMessage(),
                message);
    }

    @Test
    public void getFarewellMessage() {
        // A unit test for Ui#getFarewellMessage
        // test setup
        String message = "Bye. Hope to see you again soon!";

        // automatically verify the response
        assertEquals(ui.getFarewellMessage(),
                message);
    }

    @Test
    public void getAddMessage() {
        // A unit test for Ui#getAddMessage
        // test setup
        String message = "Got it. I've added this task:";

        // automatically verify the response
        assertEquals(ui.getAddMessage(),
                message);
    }

    @Test
    public void getDeleteMessage() {
        // A unit test for Ui#getDeleteMessage
        // test setup
        String message = "Noted. I've removed this task:";

        // automatically verify the response
        assertEquals(ui.getDeleteMessage(),
                message);
    }

    @Test
    public void getFindMessage() {
        // A unit test for Ui#getFindMessage
        // test setup
        String message = "Here are the matching tasks in your list:";

        // automatically verify the response
        assertEquals(ui.getFindMessage(),
                message);
    }

    @Test
    public void getListMessage() {
        // A unit test for Ui#getListMessage
        // test setup
        String message = "Here are the tasks in your list:";

        // automatically verify the response
        assertEquals(ui.getListMessage(),
                message);
    }

    @Test
    public void getMarkMessage() {
        // A unit test for Ui#getMarkMessage
        // test setup
        String message = "Nice! I've marked this task as done:";

        // automatically verify the response
        assertEquals(ui.getMarkMessage(),
                message);
    }

    @Test
    public void getUnmarkMessage() {
        // A unit test for Ui#getUnmarkMessage
        // test setup
        String message = "OK, I've marked this task as not done yet:";

        // automatically verify the response
        assertEquals(ui.getUnmarkMessage(),
                message);
    }

    @Test
    public void getTasksCountMessage() {
        // A unit test for Ui#getTasksCountMessage
        // test setup
        String message = "Now you have 0 tasks in the list.";

        // automatically verify the response
        assertEquals(ui.getTasksCountMessage(0),
                message);
    }

    @Test
    public void getLoadingErrorMessage() {
        // A unit test for Ui#getLoadingErrorMessage
        // test setup
        String message = "There was an error while loading from save file";

        // automatically verify the response
        assertEquals(ui.getLoadingErrorMessage(),
                message);
    }
}
