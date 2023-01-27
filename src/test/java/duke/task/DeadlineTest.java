package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testGetDescription() {
        Deadline deadline = new Deadline("Deadline Name", "1999-12-12");
        assertEquals("Deadline Name | 1999-12-12", deadline.getDescription());
    }

    @Test
    public void testGetStatus() {
        Deadline deadline = new Deadline("Deadline Name", true,"1999-12-12");
        assertEquals("true", deadline.getStatus());
    }
}
