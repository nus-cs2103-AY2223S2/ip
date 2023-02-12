package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class EventTest {
    @Test
    public void testToString() throws DukeException {
        Event event = new Event("Love Iko /from 2022-08-19 /to +999999999-12-31");
        assertEquals("[E][ ] Love Iko (from: Aug 19 2022 to: Dec 31 +999999999)", event.toString());

        event = new Event("Study at NUS /from 2019-08-03 /to 2023-05-01");
        assertEquals("[E][ ] Study at NUS (from: Aug 3 2022 to: May 1 2023", event.toString());
    }
}
