package alfred.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion_unmark() {
        String test = "[D][ ] This is a test task(by: Jan 10 2023 10:00AM)";
        assertEquals(test, new Deadline("This is a test task", "10/01/2023 1000").toString());
    }
}
