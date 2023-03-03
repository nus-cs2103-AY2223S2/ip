package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testToString() {
        Todo t = new Todo("test");
        String correctTodo = "[T][ ] test \npriority: MEDIUM";
        assertEquals(correctTodo, t.toString());
    }

    @Test
    public void testMark() {
        Todo t = new Todo("test");
        t.mark();
        String correctTodo = "[T][X] test \npriority: MEDIUM";
        assertEquals(correctTodo, t.toString());
    }
}
