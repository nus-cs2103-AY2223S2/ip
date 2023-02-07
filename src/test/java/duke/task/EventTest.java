package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;



public class EventTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private Event task = new Event("event project week ",
            LocalDateTime.parse("2019-11-15 2200", formatter), LocalDateTime.parse("2019-11-25 2359", formatter));

    @Test
    public void eventTest() {
        assertEquals("[E][ ] event project week [from: Nov 15 2019 22:00 to: Nov 25 2019 23:59]", task.toString());
    }

    @Test
    public void markEventTest() {
        task.markAsDone();
        assertEquals("[E][X] event project week [from: Nov 15 2019 22:00 to: Nov 25 2019 23:59]", task.toString());
    }
}
