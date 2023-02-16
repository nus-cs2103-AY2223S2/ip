package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
    private final Duke duke = new Duke();

    @Test
    public void getWelcome() {
        // A unit test for Duke#getWelcome
        // test setup
        String welcomeMessage = "Hello! I'm Jamie 😃\n"
                + "A personality of the Duke project.\n"
                + "What can I do for you?";

        // automatically verify the response
        assertEquals(duke.getWelcome(),
                welcomeMessage);
    }

    @Test
    public void getLoadErrorMessage() {
        // A unit test for Duke#getLoadErrorMessage
        // test setup
        String loadErrorMessage = "There was an error while loading from save file";

        // automatically verify the response
        String loadError = duke.getLoadErrorMessage();
        if (loadError != null) {
            assertEquals(loadError,
                    loadErrorMessage);
        }
    }
}
