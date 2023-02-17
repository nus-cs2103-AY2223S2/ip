package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @DisplayName("Todo toString Test")
    @Test
    public void toStringTest() {
        Todo todo = new Todo("test");
        assertEquals("[T][ ] test", todo.toString());
    }

    @DisplayName("Todo (un)marking Test")
    @Test
    public void markUnmarkTest() {
        Todo todo = new Todo("test");
        todo.markTask();
        assertEquals("[T][X] test", todo.toString());

        todo.unmarkTask();
        assertEquals("[T][ ] test", todo.toString());
    }

    @DisplayName("Todo Export Test")
    @Test
    public void exportDataTest() {
        Todo todo = new Todo("test");
        assertEquals("Todo | marked: 0 ; description: test", todo.toData());

        todo.markTask();
        assertEquals("Todo | marked: 1 ; description: test", todo.toData());
    }
}
