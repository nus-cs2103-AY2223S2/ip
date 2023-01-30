package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class ToDoTest {

    @Test
    public void toString_singleInstance_correctRepresentation() {
        Todo todo = new Todo("a");
        String expectedMessage = "[T][ ] a";
        String actualMessage = todo.toString();
        assertEquals(expectedMessage, actualMessage);
    }
}
