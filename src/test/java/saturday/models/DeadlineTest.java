package saturday.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
public class DeadlineTest {

    @Test
    public void testDeadlineConstructor() {
        Deadline deadline = new Deadline("Finish homework", "20-12-2019 23:59");
        assertNotNull(deadline);
    }

    @Test
    public void testDeadlineDescription() {
        Deadline deadline = new Deadline("Finish homework", "20-12-2019 23:59");
        assertEquals("Finish homework", deadline.getDescription());
    }

    @Test
    public void testDeadlineTime() {
        Deadline deadline = new Deadline("Finish homework", "20-12-2019 23:59");
        assertEquals("2019-12-20T23:59", deadline.getDeadline().toString());
    }

    @Test
    public void testDeadlineToString() {
        Deadline deadline = new Deadline("Finish homework", "20-12-2019 23:59");
        assertEquals("[D][ ] Finish homework (by: Dec 20 19 11:59 PM)", deadline.toString());
    }
}
