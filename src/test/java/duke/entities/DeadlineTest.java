package duke.entities;
import Nerd.entities.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void markTest() {
        Task t = new Deadline("test test", "2019-10-15");
        t.setDone();
        assertTrue(t.isDone());
    }

    @Test
    public void unmarkTest() {
        Task t = new Deadline("test test", "2019-10-15");
        t.setUndone();
        assertFalse(t.isDone());;
    }
    @Test
    public void toStringTest() {
        Task t = new Deadline("test test", "2019-10-15");
        assertEquals("[D][ ] test test (by: Oct 15 2019)", t.toString());
    }
}