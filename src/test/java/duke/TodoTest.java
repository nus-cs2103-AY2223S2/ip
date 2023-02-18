package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] return book", new Todo("return book").toString());
    }

}
