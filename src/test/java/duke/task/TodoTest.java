package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        Todo todo = new Todo("test");
        assertEquals("[T][ ] test", todo.toString());
    }

    @Test
    public void markUnmarkTest() {
        Todo todo = new Todo("test");
        todo.markTask();
        assertEquals("[T][X] test", todo.toString());

        todo.unmarkTask();
        assertEquals("[T][ ] test", todo.toString());
    }

    @Test
    public void exportDataTest() {
        Todo todo = new Todo("test");
        assertEquals("Todo | marked: 0 ; description: test", todo.toData());

        todo.markTask();
        assertEquals("Todo | marked: 1 ; description: test", todo.toData());
    }
}
