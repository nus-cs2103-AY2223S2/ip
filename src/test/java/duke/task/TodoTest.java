package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void todo_compareEquals() {
        Todo todo1 = new Todo("Do homework");
        Todo todo2 = new Todo("Sweep the floor");
        Todo todo3 = new Todo("Do homework");

        assertNotEquals(todo1, todo2);
        assertEquals(todo1, todo3);
        assertNotEquals(todo2, todo1);
        assertNotEquals(todo2, todo3);
    }
}
