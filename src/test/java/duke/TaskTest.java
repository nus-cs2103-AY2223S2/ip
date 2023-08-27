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
    public void testTaskMark() {
        ToDo todo = new ToDo("visit park");
        todo.mark();
        assertEquals("[T][X] visit park", todo.toString());
    }

    @Test
    public void testTaskUnmarked() {
        ToDo todo = new ToDo("visit park");
        todo.unmarked();
        assertEquals("[T][ ] visit park", todo.toString());
    }

    @Test
    public void testIsDone() {
        ToDo todo = new ToDo("visit park");
        todo.mark();
        assertEquals("[T][X] visit park", todo.toString());
        todo.unmarked();
        assertEquals("[T][ ] visit park", todo.toString());
    }
}
