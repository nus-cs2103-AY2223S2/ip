package duke.logic.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class EventTest {

    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("[E][ ] project meeting (from: Jan 1 2000 08:00 to: Jan 1 2030 08:00)",
                Event.create(" project meeting /from 2000-01-01 08:00 /to 2030-01-01 08:00")
                        .toString());
    }
}
