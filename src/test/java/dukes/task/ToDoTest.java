package dukes.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo newToDo = new ToDo("happy");
        assertEquals("[T][ ] happy", newToDo.toString());
    }
}