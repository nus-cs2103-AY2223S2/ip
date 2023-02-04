package fideline.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] test task (by: time)",
                new Deadline("test task", "time").toString());
    }

    @Test
    public void testDateConversion() {
        assertEquals("[D][ ] test task (by: Feb 01 3000)",
                new Deadline("test task", "3000-02-01").toString());
    }

}
