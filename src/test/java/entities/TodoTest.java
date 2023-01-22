package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;




public class TodoTest {
    @Test
    public void markTest_testIsMarked_nullReturned() {
        Task tsk = new Todo("Hello World");
        tsk.markTask();
        assertTrue(tsk.isDone);
    }

    @Test
    public void unmarkTest_testIsUnmarked_nullReturned() {
        Task tsk = new Todo("Hello World");
        tsk.unmarkTask();
        assertFalse(tsk.isDone);
    }

    @Test
    public void toStringTest_printString_validRepresentationReturned() {
        Task task = new Todo("Hello World");
        assertEquals("[T][ ] Hello World", task.toString());
    }
}
