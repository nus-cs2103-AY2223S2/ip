package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import book.task.ToDo;



public class ToDoTest {
    @Test
    public void instanceTest() {
        ToDo toDo = new ToDo("test");
        assertEquals("[T][ ] test", toDo.toString());
    }
    @Test
    public void saveStringTest() {
        ToDo toDo = new ToDo("test");
        assertEquals("T;false;test", toDo.saveString());
    }
    @Test
    public void markTest() {
        ToDo toDo = new ToDo("test");
        toDo.mark();
        assertEquals("[T][X] test", toDo.toString());
    }
    @Test
    public void unmarkTest() {
        ToDo toDo = new ToDo("test");
        toDo.mark();
        toDo.unmark();
        assertEquals("[T][ ] test", toDo.toString());
    }
}
