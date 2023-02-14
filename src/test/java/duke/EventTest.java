package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exceptions.NeroException;
import duke.task.Event;
import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testGetTaskIcon() {
        try {
            assertEquals("E", new Event("run around",
                    "2019-01-01", "2020-02-02").getTaskIcon());
        } catch (NeroException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testToString() {
        try {
            assertEquals("[E][ ] eat food from: 1 Jan 2019 to: 2 Jan 2019",
                    new Event("eat food", "2019-01-01", "2019-01-02").toString());
        } catch (NeroException e) {
            e.printStackTrace();
        }
    }
}
