package duke;

import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testStringConversion() {
        LocalDate start = LocalDate.parse("2018-03-10");
        LocalDate end = LocalDate.parse("2018-03-12");
        Event event = new Event(start, end, "camp");
        assertEquals("[E][ ] camp (from: 10 Mar 2018 to: 12 Mar 2018)", event.toString());
    }
}
