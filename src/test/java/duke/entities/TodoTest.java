package duke.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import Nerd.entities.*;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void markTest() {
        Task t = new Todo("mark test");
        t.setDone();
        assertTrue(t.isDone());
    }

    @Test
    public void unmarkTest() {
        Task t = new Todo("unmark test");
        t.setUndone();
        assertFalse(t.isDone());
    }
    @Test
    public void toStringTest() {
        Task task = new Todo("Hello World");
        assertEquals("[T][ ] Hello World", task.toString());
    }
}
