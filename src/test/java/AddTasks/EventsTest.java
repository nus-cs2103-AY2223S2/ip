package AddTasks;
import jdk.jfr.Event;
import munch.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventsTest {
    @Test
    public void testEventTasks() {
        String from = "01/02/2023";
        String to = "02/02/2023";
        LocalDate convertDateFrom = Parser.convertToDate(from);
        LocalDate convertDateTo = Parser.convertToDate(to);
        Events event1 = new Events("read book", convertDateFrom, convertDateTo);
        assertEquals(" [E][ ] read book (From: Feb 1 2023 | To: Feb 2 2023)", event1.toString());

        Events event2 = new Events("read book", convertDateFrom, convertDateTo);
        event2.markAsDone();
        assertEquals(" [E][X] read book (From: Feb 1 2023 | To: Feb 2 2023)", event2.toString());
        event2.markAsUndone();
        assertEquals(" [E][ ] read book (From: Feb 1 2023 | To: Feb 2 2023)", event2.toString());
    }
}