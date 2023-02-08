package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void event_compareEquals() {
        Event event1 = new Event("Attend birthday", "2000-01-04 2000", "2000-01-04 2300");
        Event event2 = new Event("Attend birthday", "2000-04-01 2000", "2000-01-04 2300");
        Event event3 = new Event("Yearly carnival", "2000-01-04 2000", "2000-01-04 2300");
        Event event4 = new Event("Attend birthday", "2000-01-04 2000", "2000-01-04 2200");
        Event event5 = new Event("Attend birthday", "2000-01-04 2000", "2000-01-04 2300");
        assertNotEquals(event1, event2);
        assertNotEquals(event1, event3);
        assertNotEquals(event1, event4);
        assertEquals(event1, event5);
        assertNotEquals(event2, event3);
        assertNotEquals(event2, event4);
        assertNotEquals(event3, event4);
    }

    @Test
    public void event_incorrectFormat_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, (() -> new Event(
                "Attend talk", "2020-06-11 0900", "2020-06-11")));
        assertThrows(IllegalArgumentException.class, (() -> new Event(
                "Attend talk", "1800", "2020-07-11 2000")));
        assertThrows(IllegalArgumentException.class, (() -> new Event(
                "Attend talk", "2020-06-11", "2020-06-11 2300")));
        assertThrows(IllegalArgumentException.class, (() -> new Event(
                "Attend talk", "2020-07-11 1800", "2020-07-11")));
        assertThrows(IllegalArgumentException.class, (() -> new Event(
                "Attend talk", "2020-13-02 1500", "2020-13-02 1700")));
    }
}
