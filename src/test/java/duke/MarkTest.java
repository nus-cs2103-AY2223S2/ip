package duke;
import org.junit.jupiter.api.Test;

import model.tasks.Task;
import model.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkTest {
    @Test
    public void testMark1() {
        Task todo = new Todo("See the sunrise", true);
        String result = todo.toString();
        assertEquals("[T][X] See the sunrise", result);
    }
}
