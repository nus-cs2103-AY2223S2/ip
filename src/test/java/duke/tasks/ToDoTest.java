package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][x] test 1 2 3", new ToDo("test 1 2 3", true).toString());
    }
}
