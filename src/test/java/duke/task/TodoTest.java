package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testToString() {

        Task todo = new Todo("eat");
        assertEquals(todo.toString(), "[T][ ] eat");

    }

    @Test
    public void testMark() {
        Task todo = new Todo("eat");
        todo.markAsDone();
        assertEquals(todo.toString(), "[T][X] eat");
    }

}
