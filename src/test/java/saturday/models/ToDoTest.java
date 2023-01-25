package saturday.models;

import org.junit.jupiter.api.Test;
import saturday.models.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testToDoConstructor() {
        ToDo toDo = new ToDo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", toDo.toString());
    }

    @Test
    public void testToDoToString() {
        ToDo toDo = new ToDo("Buy groceries");
        toDo.mark();
        assertEquals("[T][X] Buy groceries", toDo.toString());
    }
}
