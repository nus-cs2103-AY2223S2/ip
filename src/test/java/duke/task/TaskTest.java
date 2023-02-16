package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void setAsDone() {
        String name = "isdone";
        Task a = new Task(name);
        Task b = new Task(name);
        b.setAsDone();

        // test here
        a.setAsDone();
        b.setAsDone();
        assertTrue(a.isMarkedDone());
        assertTrue(b.isMarkedDone());
    }

    @Test
    public void setAsNotDone() {
        String name = "isdone";
        Task a = new Task(name);
        Task b = new Task(name);
        b.setAsDone();

        // test here
        a.setAsNotDone();
        b.setAsNotDone();
        assertFalse(a.isMarkedDone());
        assertFalse(b.isMarkedDone());
    }

    @Test
    public void testStringConversion() {
        String name = "Very long name with many spaces";
        Task a = new Task(name);

        assertEquals("[ ] " + name, a.toString());
    }

}
