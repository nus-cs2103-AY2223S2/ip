package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void test() {
        assertEquals("[T][ ] clean house", new Todo("todo", "clean house").toString());
    }
}
