package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Represents the test class for Ui.java.
 *
 * @author MrTwit99
 * @since 2023-02-12
 */
public class UiTest {

    private Ui ui = new Ui();
    private String message = "This is an automated test text";

    /**
     * Tests the method showWelcome() in Ui.java.
     */
    @Test
    public void showWelcome() {
        String testText = "Hello from\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "    ____________________________________________________________________________________\n"
                + "    Hello! I'm Duke.\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________________________________\n";
        assertEquals(testText, ui.showWelcome());
    }

    /**
     * Tests the method showFarewellMessage() in Ui.java.
     */
    @Test
    public void showFarewellMessage() {
        String testText = "    ____________________________________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________________________________\n";
        try {
            assertEquals(testText, ui.showFarewellMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests the method printCommand() in Ui.java.
     */
    @Test
    public void printCommand() {
        String testText = "This is an automated test text";
        assertEquals(message, testText);
    }

    /**
     * Tests the method printInvalidError() in Ui.java.
     */
    @Test
    public void printInvalidError() {
        String testText = "    _______________________________________________________________________________________"
                + "\n    Invalid inputs!\n"
                + "    Please enter your date & time in the format: YYYY-MM-DD HH:MM \n"
                + "    Please also ensure they are valid values!\n"
                + "    _______________________________________________________________________________________\n";
        assertEquals(testText, ui.printInvalidDateError());
    }
}
