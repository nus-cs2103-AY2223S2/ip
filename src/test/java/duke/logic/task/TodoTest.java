package duke.logic.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TodoTest {

    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("[T][ ] brush teeth", Todo.create(" brush teeth").toString());
    }
}
