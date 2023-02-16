package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    private ToDo todo = new ToDo("Read book");
    @Test
    public void initialiseTest() {
        assertEquals(todo.toString(), "[T][ ] Read book");
    }

    @Test
    public void markTaskTest() {
        todo.setDone();
        assertEquals(todo.toString(), "[T][X] Read book");
    }

    @Test
    public void unmarkTaskTest() {
        todo.setUndone();
        assertEquals(todo.toString(), "[T][ ] Read book");
    }
}
