package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventsTest {

    @Test
    public void datetimeFormat_noStartDate_success() {
        assertEquals("Monday", new Events("return book", "Monday", "Tuesday").localDateParser("Monday"));
    }

    @Test
    public void datetimeFormat_noEndDate_success() {
        assertEquals("Tuesday", new Events("return book", "Monday", "Tuesday").localDateParser("Tuesday"));
    }

    @Test
    public void testDatetimeStartConversion() {
        assertEquals("20/01/2023", new Events("borrow book", "20/01/2023", "25/01/2023").localDateParser("20/01/2023"));
    }

    @Test
    public void testDatetimeEndConversion() {
        assertEquals("25/01/2023", new Events("borrow book", "20/01/2023", "25/01/2023").localDateParser("25/01/2023"));
    }

    @Test
    public void testMarkDone() {
        assertEquals("X", new Events("return book", "Monday", "Tuesday").markAsDone().getStatusIcon());
    }

    @Test
    public void testMarkUndone() {
        assertEquals(" ", new Events("return book", "Monday", "Tuesday").markAsUndone().getStatusIcon());
    }

    @Test
    public void testToString() {
        assertEquals("[E][ ] return book(from: Monday)(to: Tuesday)",
                new Events("return book", "Monday", "Tuesday").toString());
    }

}
