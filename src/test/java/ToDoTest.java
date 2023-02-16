package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toStringTest() {
        ToDo todo = new ToDo("Wash car");
        assertEquals("T |   | Wash car", todo.toString());
    }
}
