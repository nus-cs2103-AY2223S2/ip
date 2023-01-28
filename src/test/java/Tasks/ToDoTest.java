package Tasks;

import org.junit.jupiter.api.Test;

import Commands.TodoCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    ToDo todo = new ToDo("Read book");
    @Test
    public void initialiseTest(){
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