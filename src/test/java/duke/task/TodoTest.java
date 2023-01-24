package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    void testToString() {
        Todo t = new Todo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", t.toString());
    }
}
