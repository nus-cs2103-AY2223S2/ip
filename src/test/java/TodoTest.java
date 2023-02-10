package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void test() {
        assertEquals("[T][ ] clean house", new Todo("todo", "clean house").toString());
    }
}
