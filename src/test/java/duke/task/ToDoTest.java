package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void getters_validToDo_stringReturned() {
        ToDo task = new ToDo("Item");
        assertEquals(task.getType(), "T");
        assertEquals(task.toString(), "[T][ ] Item");
    }
}
