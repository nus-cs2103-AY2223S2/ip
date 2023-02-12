package rick.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a test suite that tests Exceptions that are not commonly
 * generated in other testcases.
 *
 * @author SeeuSim
 *         AY22/23-S2 CS2103T
 */
public class TestExceptions {
    /**
     * Tests the {@code RickInvalidCommandException} class for its message.
     */
    @Test
    public void testInvalidCommandException() {
        RickException re = new RickInvalidCommandException();
        String expected = "I don't know that command yet. Give me another!";
        assertEquals(expected, re.getMessage());
    }

    /**
     * Tests the {@code TaskListFullException} class for its message.
     */
    @Test
    public void testTaskListFullException() {
        RickException re = new TaskListFullException();
        String expected = "Rick is busy and can't take any more tasks";
        assertEquals(expected, re.getMessage());
    }

}
