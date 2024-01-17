package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class ToDoTest {

    @Test
    public void toString_singleInstance_correctRepresentation() {
        String descr = "Todo test";
        Todo todo = new Todo(descr);
        String expectedMessage = "Todo test";
        String actualMessage = todo.toString();
        assertEquals(expectedMessage, actualMessage);
    }
}
