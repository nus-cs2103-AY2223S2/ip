package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testGetTaskIcon() {
        assertEquals("T", new ToDo("run around").getTaskIcon());
    }

    @Test
    public void testToString() {
        assertEquals("[T][ ] eat food", new ToDo("eat food").toString());
    }
}
