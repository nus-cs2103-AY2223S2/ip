package james;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import james.task.Deadline;
import james.task.ToDo;

public class ToDoTest {
    @Test
    public void toString_nonNullTask_success() {
        ToDo task = new ToDo("read book");
        assertEquals("[T][ ] read book", task.toString());
    }

    @Test
    public void toStoreString_nonNullTask_success() {
        Deadline task = new Deadline("homework", "25/03/2000 1800");
        assertEquals("D | 0 | homework | 25/03/2000 1800", task.toStoreString());
    }
}
