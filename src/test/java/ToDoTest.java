
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.ToDo;

public class ToDoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ]read book",
                new ToDo("read book").toString());
    }
}