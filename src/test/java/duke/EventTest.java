package duke;
import duke.Duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class EventTest {

    @Test
    public void testString1() {
        String s1 = "2023-09-09 18:55";
        String s2 = "2021-09-18 19:20";
        LocalDateTime start = LocalDateTime.parse(s1, Duke.getFormatter());
        LocalDateTime end = LocalDateTime.parse(s2, Duke.getFormatter());
        Event e = new Event("mark show", false, start, end);
        assertEquals("[E][ ]mark show (from: 9 Sep 2023 6.55pm to: 18 Sep 2021 7.20pm)", e.toString());
    }

    @Test
    public void testString2() {
        String s1 = "2023-02-03 23:59";
        String s2 = "2021-09-30 00:00";
        LocalDateTime start = LocalDateTime.parse(s1, Duke.getFormatter());
        LocalDateTime end = LocalDateTime.parse(s2, Duke.getFormatter());
        Event e = new Event("mark show", true, start, end);
        assertEquals("[E][X]mark show (from: 3 Feb 2023 11.59pm to: 30 Sep 2021 12.00am)", e.toString());
    }

    @Test
    public void testStatusTrueString() {
        String s1 = "2000-01-01 01:01";
        String s2 = "2001-02-02 02:30";
        LocalDateTime start = LocalDateTime.parse(s1, Duke.getFormatter());
        LocalDateTime end = LocalDateTime.parse(s2, Duke.getFormatter());
        Event e = new Event("hug loved ones", true, start, end);
        assertEquals("EVENT / 1 / hug loved ones / " + s1 + " / " + s2, e.formatStringForFile());
    }

    @Test
    public void testStatusFalseString() {
        String s1 = "2000-01-11 22:01";
        String s2 = "2001-11-08 16:30";
        LocalDateTime start = LocalDateTime.parse(s1, Duke.getFormatter());
        LocalDateTime end = LocalDateTime.parse(s2, Duke.getFormatter());
        Event e = new Event("exam", false, start, end);
        assertEquals("EVENT / 0 / exam / " + s1 + " / " + s2, e.formatStringForFile());
    }
}
