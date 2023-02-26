package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void descriptionTest() {
        Event event = new Event("Party", "2023-02-01 18:00", "2023-02-01 22:00");
        assertEquals("Party", event.getDescription());
    }

    @Test
    public void fromTimeTest() {
        Event event = new Event("Party", "2023-02-01 18:00", "2023-02-01 22:00");
        assertEquals("2023-02-01 18:00", event.getFrom());
    }

    @Test void toTimeTest() {
        Event event = new Event("Party", "2023-02-01 18:00", "2023-02-01 22:00");
        assertEquals("2023-02-01 22:00", event.getTo());
    }
}
