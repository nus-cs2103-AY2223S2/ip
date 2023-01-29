package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testTask() {
        ToDo todo = new ToDo("visit park");
        assertEquals("[T][ ] visit park", todo.toString());
    }
    @Test
    public void testTaskMarkMethod() {
        ToDo todo = new ToDo("visit park");
        todo.mark();
        assertEquals("[T][X] visit park", todo.toString());
    }

    @Test
    public void testTaskUnmarkedMethod() {
        ToDo todo = new ToDo("visit park");
        todo.unmarked();
        assertEquals("[T][ ] visit park", todo.toString());
    }

    @Test
    public void testIsDoneMethod() {
        ToDo todo = new ToDo("visit park");
        todo.mark();
        assertEquals("[T][X] visit park", todo.toString());
        todo.unmarked();
        assertEquals("[T][ ] visit park", todo.toString());
    }
}
