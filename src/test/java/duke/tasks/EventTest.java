package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class EventTest {
    @Test
    public void eventTest() {
        try {
            Event e = new Event("meeting", "2024-01-20 1400", "2024-01-21 1400");
            assertEquals("[E] [   ]  meeting\n (from: Jan 20 2024 02:00 PM to: Jan 21 2024 02:00 PM)\n", e.toString());
        } catch (DukeException e) {
            e.getMessage();
        }
    }
}
