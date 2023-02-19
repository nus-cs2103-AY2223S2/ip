package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testToString() {
        ToDo todo = new ToDo("Eat food");
        assertEquals("[T][ ][LOW] Eat food", todo.toString());
    }
}