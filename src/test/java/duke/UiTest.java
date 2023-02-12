package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents the test class for Ui.java.
 *
 * @author MrTwit99
 * @since 2023-02-12
 */
public class UiTest {

    Ui ui= new Ui();

    /**
     * Tests the method showWelcome() in Ui.java.
     */
    @Test
    public void showWelcome() {
        String text = "Hello from\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "    ____________________________________________________________________________________\n"
                + "    Hello! I'm Duke.\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________________________________\n";
        assertEquals(text, ui.showWelcome());
    }
}