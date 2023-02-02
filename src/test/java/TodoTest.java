package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    
    @Test
    public void createTodoTest() {
        Todo todo = new Todo("name");
        assertEquals("[T][ ] name", todo.toString());
    }

    @Test
    public void markTest() {
        Todo todo = new Todo("name");
        todo.markAsDone();
        assertEquals("[T][X] name", todo.toString());
    }

    @Test
    public void unmarkTest() {
        Todo todo = new Todo("name");
        todo.markAsDone();
        todo.undoTask();
        assertEquals("[T][ ] name", todo.toString());
    }

    @Test
    public void checkTypeTest() {
        Todo todo = new Todo("name");
        assertEquals("T", todo.getType());
    }

}