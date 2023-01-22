package duke.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class EventTest {
    @Test
    public void testToString() {
        LocalDateTime from = LocalDateTime.parse("2022-01-01T00:00");
        LocalDateTime to = LocalDateTime.parse("2023-01-01T00:00");
        assertEquals("[E][ ] test (from: Jan 01 2022 12:00AM to: Jan 01 2023 12:00AM)",
                new Event("test", from, to).toString());
    }
}
