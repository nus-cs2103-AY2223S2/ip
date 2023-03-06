package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    void testToString() {
        Todo t = new Todo("Buy groceries");
        assertEquals("[T][   ] Buy groceries", t.toString());
    }
}
