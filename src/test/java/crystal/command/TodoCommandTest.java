package crystal.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents the TodoCommandTest class.
 *
 */
public class TodoCommandTest {

    /**
     * Test if the Todo Command class is working as intended.
     *
     */
    @Test
    public void testTodoCommand() {
        Command c = new TodoCommand("buy book");
        assertEquals(false, c.isExit());
    }
}
