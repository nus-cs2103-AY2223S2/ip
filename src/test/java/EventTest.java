package duke.task;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        Event event = new Event("Group meeting", LocalDateTime.of(2024, 1, 27, 1, 27),
                LocalDateTime.of(2024, 1, 27, 2, 27));
        assertEquals("E |   | Group meeting | from: Jan 27 2024 01:27 to: Jan 27 2024 02:27", event.toString());
    }
}
