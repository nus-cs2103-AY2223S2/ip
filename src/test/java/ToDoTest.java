package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toStringTest() {
        ToDo todo = new ToDo("Wash car");
        assertEquals("T |   | Wash car", todo.toString());
    }
}
