package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void test() {
        Event event1 = new Event("party", "Saturday 2-9pm");
        event1.markAsDone();
        assertEquals("[E][X] party (at: Saturday 2-9pm)", event1.toString());
        Event event2 = new Event("reunion", "Sunday 3-7pm");
        assertEquals("[E][ ] reunion (at: Sunday 3-7pm)", event2.toString());
        Event event3 = new Event("", "Monday 1pm");
        assertEquals("[E][ ]  (at: Monday 1pm)", event3.toString());
    }
}
