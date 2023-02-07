package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventTest() {
        Event e = new Event("meeting", "2024-01-20 1400", "2024-01-21 1400");
        assertEquals("[E] [ ] meeting\n (from: Jan 20 2024 02:00 PM to: Jan 21 2024 02:00 PM)", e.toString());
    }
}
