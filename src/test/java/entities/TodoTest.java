package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void markTest(){
        Task tsk = new Todo("Hello World");
        tsk.markTask();
        assertTrue(tsk.isDone);
    }

    @Test
    public void unmarkTest(){
        Task tsk = new Todo("Hello World");
        tsk.unmarkTask();
        assertFalse(tsk.isDone);
    }

    @Test
    public void toStringTest() {
        Task task = new Todo("Hello World");
        assertEquals("[T][ ] Hello World", task.toString());
    }
}
