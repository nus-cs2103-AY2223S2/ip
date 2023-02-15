package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testGetDescription() {
        Deadline deadline = new Deadline("Deadline Name", "12/12/1999 1300");
        assertEquals("Deadline Name | 12/12/1999 1300", deadline.getDescription());
    }

    @Test
    public void testGetStatus() {
        Deadline deadline = new Deadline("Deadline Name", true, "12/12/1999 1300");
        assertEquals("true", deadline.getStatus());
    }
}
