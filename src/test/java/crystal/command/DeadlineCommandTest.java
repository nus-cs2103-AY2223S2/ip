package crystal.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents the DeadlineCommandTest class.
 *
 */
public class DeadlineCommandTest {

    /**
     * Test if the Deadline Command class is working as intended.
     *
     */
    @Test
    public void testDeadlineCommand() {
        Command c = new DeadlineCommand("buy book", "2019-10-15T18:00");
        assertEquals(false, c.isExit());
    }
}
