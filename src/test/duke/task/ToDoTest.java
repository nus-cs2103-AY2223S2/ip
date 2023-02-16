package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    private ToDo todo = new ToDo("read book");
    @Test
    public void addToDo(){
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void markToDo(){
        todo.mark();
        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    public void unmarkToDo(){
        todo.unmark();
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void saveToDo(){
        assertEquals("todo false\nread book", todo.getDetailsToSave());
    }
}