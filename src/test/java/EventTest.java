import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {
    /** Test the creation of event task. */
    @Test
    public void addEventTest() {
        Event event = new Event("HDL eating competition ", "2019-10-16", "2019-10-17");

        assertEquals("[E][ ] HDL eating competition (from: Oct 16 2019 to: Oct 17 2019)", event.toString());
    }

    /** Test the marking of event task. */
    @Test
    public void markTest() {
        Event event = new Event("HDL eating competition ", "2019-10-16", "2019-10-17");
        event.mark();

        assertEquals("[E][X] HDL eating competition (from: Oct 16 2019 to: Oct 17 2019)", event.toString());
    }

    /** Test the unmarking of event task. */
    @Test
    public void unmarkTest() {
        Event event = new Event("HDL eating competition ", "2019-10-16", "2019-10-17");
        event.mark();
        event.unmark();

        assertEquals("[E][ ] HDL eating competition (from: Oct 16 2019 to: Oct 17 2019)", event.toString());
    }
}
