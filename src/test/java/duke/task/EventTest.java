package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getters_validEvent_stringReturned() {
        Event task = null;
        try {
            task = new Event("Activity",
                    "2023-01-01 23:59",
                    "2023-01-02 23:59");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(task.getFrom(), "2023-01-01 23:59");
        assertEquals(task.getTo(), "2023-01-02 23:59");
        assertEquals(task.getType(), "E");
        assertEquals(task.toString(), "[E][ ] Activity (from: Jan 01 2023 23:59 "
                + "to: Jan 02 2023 23:59)");
    }
}
