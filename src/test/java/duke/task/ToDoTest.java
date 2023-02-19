package duke.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ToDoTest {
    @Test
    public void testToDo() {
        ToDo todo = new ToDo("test");

        assertEquals("test", todo.getTask());
        assertFalse(todo.isDone());
    }

    @Test
    public void testToDoDone() {
        ToDo todo = new ToDo("test");
        todo.markAsDone();

        assertEquals("test", todo.getTask());
        assertTrue(todo.isDone());
    }

    @Test
    public void testToDoToString() {
        ToDo todo = new ToDo("test");
        assertEquals("[T][ ] test", todo.toString());
    }

    @Test
    public void testToDoDoneToString() {
        ToDo todo = new ToDo("test");
        todo.markAsDone();
        assertEquals("[T][X] test", todo.toString());
    }

    @Test
    public void testToDoToFileString() {
        ToDo todo = new ToDo("test");
        assertEquals("T | 0 | test", todo.toFileString());
    }

    @Test
    public void testToDoDoneToFileString() {
        ToDo todo = new ToDo("test");
        todo.markAsDone();
        assertEquals("T | 1 | test", todo.toFileString());
    }

    @Test
    public void testToDoEquals() {
        ToDo todo = new ToDo("test");
        ToDo todo2 = new ToDo("test");
        assertEquals(todo, todo2);
    }

    @Test
    public void testToDoNotEquals() {
        ToDo todo = new ToDo("test");
        ToDo todo2 = new ToDo("test2");
        assertFalse(todo.equals(todo2));
    }
}
