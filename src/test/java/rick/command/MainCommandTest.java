package rick.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


/**
 * Represents a test suite that tests all commands that affect the App's
 * operation:
 * <ul>
 *     <li>{@code ExitCommand}</li>
 *     <li>{@code ErrorCommand}</li>
 * </ul>
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class MainCommandTest extends CommandTest {
    /**
     * Tests the {@code ErrorCommand} class.
     */
    @Test
    public void testErrorCommand() {
        Command error = new ErrorCommand("This is an error");
        String actualUi = error.execute(TASK_LIST, UI);

        String expectedUi = "This is an error";
        assertEquals(expectedUi, actualUi);
    }

    /**
     * Tests the {@code ExitCommand} class.
     */
    @Test
    public void testExitCommand() {
        Command exit = new ExitCommand();
        assertTrue(exit.isExit());
        String actualUi = exit.execute(TASK_LIST, UI);

        String expectedUi = "It was okay serving you. Might/might not see you again.\n"
                + "Exiting...";
        assertEquals(expectedUi, actualUi);
    }
}
