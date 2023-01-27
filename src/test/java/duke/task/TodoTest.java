package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("[T][ ] brush teeth", Todo.create(" brush teeth").toString());
    }
}
