package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testEncode() {
        ToDo todo = new ToDo("Todo Name", true);
        assertEquals("Todo | true | Todo Name", todo.encode());
    }
}
