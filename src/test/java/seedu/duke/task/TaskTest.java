package seedu.duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testStringConversion() {
        assertEquals("[ ] 123",
                new Task("123").toString());
    }

    @Test
    public void getStatusIconSuccess() {
        assertEquals(" ",
                new Task("123").getStatusIcon());
    }

    @Test
    public void getDescriptionSuccess() {
        assertEquals("123",
                new Task("123").getDescription());
    }
}
