package crystal.task;

import crystal.CrystalException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents the EventTest class.
 *
 */
public class EventTest  {

    /**
     * test if the Event class is working as intended
     *
     */
    @Test
    public void testEvent() throws CrystalException {
        Event event = new Event("project meeting", "Mon 2pm", "4pm");
        assertEquals("[E][ ] project meeting (from: Mon 2pm to: 4pm)", event.toString());
    }
}
