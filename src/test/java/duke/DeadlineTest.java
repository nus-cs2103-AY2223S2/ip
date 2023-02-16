package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    public void datetimeFormat_noDate_success() {
        assertEquals("Sunday", new Deadline("return book", "Sunday").localDateParser());
    }

    @Test
    public void testDatetimeConversion() {
        assertEquals("20/01/2023", new Deadline("return book", "20/01/2023").localDateParser());
    }

    @Test
    public void testMarkDone() {
        assertEquals("X", new Deadline("return book", "Sunday").markAsDone().getStatusIcon());
    }

    @Test
    public void testMarkUndone() {
        assertEquals(" ", new Deadline("return book", "Sunday").getStatusIcon());
    }

    @Test
    public void testToString() {
        assertEquals("[D][ ] return book (by: Sunday)", new Deadline("return book", "Sunday").toString());
    }
}
