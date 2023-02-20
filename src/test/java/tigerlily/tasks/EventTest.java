package tigerlily.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void outputTest() {
        Event testEvent = new Event("test",
                LocalDateTime.parse("2023-02-14 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse("2023-02-14 20:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        assertEquals("[E][ ] test (from: 14/02/2023 18:00 to: 20:00)", testEvent.toString());
    }
}