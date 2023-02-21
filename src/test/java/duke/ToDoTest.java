package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void addToDo() {
        ToDo toDo = new ToDo("testing ToDo");
        assertNotNull(toDo);
        assertEquals(toDo.getName(), "testing ToDo");
    }

    @Test
    public void checkMarkAndUnmark() {
        ToDo toDo = new ToDo("testing ToDo");
        assertEquals(toDo.isDone(), false);
        toDo.setMarked();
        assertEquals(toDo.isDone(), true);
        toDo.setUnmarked();
        assertEquals(toDo.isDone(), false);
    }
}
