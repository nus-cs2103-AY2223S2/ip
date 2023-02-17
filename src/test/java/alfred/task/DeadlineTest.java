package alfred.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import alfred.exceptions.AlfredException;


public class DeadlineTest {

    @Test
    public void testStringConversion_deadline_noException() {
        String test = "[D][ ] This is a test task(by: Jan 10 2023 10:00AM)";
        try {
            assertEquals(test, new Deadline("This is a test task", "10/01/2023 1000").toString());
        } catch (AlfredException e) {
            fail("Exception thrown");
        }
    }

    @Test
    public void createDeadline_exceptionThrown() {
        AlfredException e = assertThrows(AlfredException.class, () -> {
            new Deadline("This is a test task", "10/012023");
        });

        String expectedMessage = "ERROR! The date format should be given as d/MM/yyyy HHmm\n";
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
