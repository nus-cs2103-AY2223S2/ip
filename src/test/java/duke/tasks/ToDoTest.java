import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.ToDo;

public class ToDoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][x] test 1 2 3", new ToDo("test 1 2 3", true).toString());
    }

}
