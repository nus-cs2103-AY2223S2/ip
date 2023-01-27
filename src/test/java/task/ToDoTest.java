package task;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toString_singleInstance_correctRepresentation() {
        Todo todo = new Todo("a");
        String expectedMessage = "[T][ ] a";
        String actualMessage = todo.toString();
        assertEquals(expectedMessage, actualMessage);
    }
}
