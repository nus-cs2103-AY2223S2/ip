package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToDoEventDescription(){
        ToDo todo = new ToDo("todo", "buy book", false);
        String expected = "[T][ ] buy book";
        assertEquals(expected, todo.provideDetails());
    }
}
