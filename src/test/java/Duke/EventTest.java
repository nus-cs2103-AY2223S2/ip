package duke;

import duke.Tasks.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTest {
    public static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void markTest() {
        Event e = new Event("Sleep", LocalDateTime.parse("2020-01-01 13:00", format),
                LocalDateTime.parse("2023-01-02 15:00",format), false);
        assertEquals(e.mark().toString(), "[E][X] Sleep (from: Jan 01 2020 13:00 to Jan 02 2023 15:00)");
    }

    @Test
    public void reformatTest() {
        Event e = new Event("Watch anime", LocalDateTime.parse("2020-01-01 18:00", format),
                LocalDateTime.parse("2023-05-02 15:00",format), false);
        assertEquals(e.reformat(), "E | 0 | Watch anime | 2020-01-01 18:00 | 2023-05-02 15:00");
    }

}
