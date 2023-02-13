package duke.Task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testFormatting() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse("2023-02-12 1400", formatter);
        LocalDateTime until = LocalDateTime.parse("2023-02-12 1600", formatter);
        Event event = new Event("do CS2103 ip", from, until);
        assertEquals("do CS2103 ip (from: 12/02/2023 14:00 to: 12/02/2023 16:00)", event.toString());
    }
}
