package duke.entities;
import Nerd.entities.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void markTest() {
        Task t = new Event("test test", "2019-10-15", "2019-10-17");
        t.setDone();
        assertTrue(t.isDone());
    }

    @Test
    public void unmarkTest() {
        Task t = new Event("test test", "2019-10-15", "2019-10-17");
        t.setUndone();
        assertFalse(t.isDone());;
    }
    @Test
    public void toStringTest() {
        Task t = new Event("test test", "2019-10-15", "2019-10-17");
        assertEquals("[E][ ] test test (from: Oct 15 2019 to: Oct 17 2019)", t.toString());
    }
}