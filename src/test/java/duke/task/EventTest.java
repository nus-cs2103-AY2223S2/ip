package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString() {
        try {
            Event event = new Event("Eat food", "08/02/2023 16:00", "08/02/2023 18:00");
            assertEquals("[E][ ][LOW] Eat food (from: 08 Feb 2023 4 PM to: 08 Feb 2023 6 PM)", event.toString());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testGetFrom() {
        try {
            Event event = new Event("Eat food", "08/02/2023 16:00", "08/02/2023 18:00");
            assertEquals("08/02/2023 16:00", event.getFrom());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testGetTo() {
        try {
            Event event = new Event("Eat food", "08/02/2023 16:00", "08/02/2023 18:00");
            assertEquals("08/02/2023 18:00", event.getTo());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
