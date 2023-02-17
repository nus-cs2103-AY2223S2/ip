package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testEncode() {
        ToDo todo = new ToDo("Todo Name", true);
        assertEquals("Todo | true | Todo Name", todo.encode());
    }
}
