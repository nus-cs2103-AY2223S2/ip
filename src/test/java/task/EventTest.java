package task;

import org.junit.jupiter.api.Test;
import task.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.DukeException;

public class EventTest {
    @Test
    public void testToString() throws DukeException {
        Event event = new Event("Love Iko /from 2022-08-19 /to +999999999-12-31");
        assertEquals("[E][ ] Love Iko (from: Aug 19 2022 to: Dec 31 +999999999)", event.toString());
    }
}
