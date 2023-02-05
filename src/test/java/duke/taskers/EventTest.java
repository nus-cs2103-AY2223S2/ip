package duke.taskers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void testString1() {
        String s1 = "2023-09-09 18:55";
        String s2 = "2021-09-18 19:20";
        LocalDateTime start = LocalDateTime.parse(s1, FORMATTER);
        LocalDateTime end = LocalDateTime.parse(s2, FORMATTER);
        Event e = new Event("mark show", false, false, start, end);
        assertEquals("[E][ ][ ]mark show (from: 9 Sep 2023 6.55pm to: 18 Sep 2021 7.20pm)", e.toString());
    }

    @Test
    public void testString2() {
        String s1 = "2023-02-03 23:59";
        String s2 = "2021-09-30 00:00";
        LocalDateTime start = LocalDateTime.parse(s1, FORMATTER);
        LocalDateTime end = LocalDateTime.parse(s2, FORMATTER);
        Event e = new Event("mark show", true, false, start, end);
        assertEquals("[E][X][ ]mark show (from: 3 Feb 2023 11.59pm to: 30 Sep 2021 12.00am)", e.toString());
    }

    @Test
    public void testStatusTrueString() {
        String s1 = "2000-01-01 01:01";
        String s2 = "2001-02-02 02:30";
        LocalDateTime start = LocalDateTime.parse(s1, FORMATTER);
        LocalDateTime end = LocalDateTime.parse(s2, FORMATTER);
        Event e = new Event("hug loved ones", true, false, start, end);
        assertEquals("EVENT / 1 / 0 / hug loved ones / " + s1 + " / " + s2, e.formatStringForFile());
    }

    @Test
    public void testStatusFalseString() {
        String s1 = "2000-01-11 22:01";
        String s2 = "2001-11-08 16:30";
        LocalDateTime start = LocalDateTime.parse(s1, FORMATTER);
        LocalDateTime end = LocalDateTime.parse(s2, FORMATTER);
        Event e = new Event("exam", false, false, start, end);
        assertEquals("EVENT / 0 / 0 / exam / " + s1 + " / " + s2, e.formatStringForFile());
    }
}
