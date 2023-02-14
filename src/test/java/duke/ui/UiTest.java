package duke.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void welcomeMessage() {
        assertEquals("Hello! I'm Duke\nWhat can I do for you?", new Ui().showWelcomeMessage());
    }

    @Test
    public void GoodbyeMessage() {
        assertEquals("Bye. Hope to see you again soon!", new Ui().showGoodbyeMessage());
    }
}
