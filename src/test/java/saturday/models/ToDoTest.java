package saturday.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
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
