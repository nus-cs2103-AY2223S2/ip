package rick;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


/**
 * Represents a test suite that tests the Rick instance.
 */
public class RickTest {
    private static Rick r;

    /**
     * Instantiates and populates the test suite with a Rick instance.
     */
    @BeforeAll
    public static void initRick() {
        RickTest.r = new Rick();
    }

    /**
     * Tests the welcome message.
     */
    @Test
    public void testRick() {
        String wc = r.getWelcome();
        assertEquals("Hello, I'm R-r-r-rickkk!\n"
                + "What's up?", wc
        );
    }

    /**
     * Tests the GUI exit flag functionality.
     */
    @Test
    public void testIsCloseAppTime() {
        boolean beforeExitCommand = r.isCloseAppTime();
        r.getResponse("mark 1");
        boolean afterNonExitCommand = r.isCloseAppTime();
        r.getResponse("bye");
        boolean afterExitCommand = r.isCloseAppTime();
        assertAll(() -> assertFalse(beforeExitCommand
                ), () -> assertFalse(afterNonExitCommand
                ), () -> assertTrue(afterExitCommand)
        );
    }

    /**
     * Tests the Rick instance for its ability to generate UI, given user commands.
     */
    @Test
    public void testGetResponse() {
        String actualExitMessage = r.getResponse("bye");
        String expectedExitMessage = "It was okay serving you. Might/might not see you again.\n"
                + "Exiting...";
        assertEquals(expectedExitMessage, actualExitMessage);
    }
}
