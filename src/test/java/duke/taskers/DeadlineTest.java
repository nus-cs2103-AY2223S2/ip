package duke.taskers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.Duke;



public class DeadlineTest {


    @Test
    public void testStringPrint1() {
        String s = "2002-02-02 12:30";
        LocalDateTime end = LocalDateTime.parse(s, Duke.getFormatter());
        Deadline d = new Deadline("do homework", false, false, end);
        assertEquals("[D][ ][ ]do homework (by: 2 Feb 2002 12.30pm)", d.toString());
    }

    @Test
    public void testStringPrint2() {
        String s = "2001-01-01 13:34";
        LocalDateTime end = LocalDateTime.parse(s, Duke.getFormatter());
        Deadline d = new Deadline("get birthday present", true, false, end);
        assertEquals("[D][X][ ]get birthday present (by: 1 Jan 2001 1.34pm)", d.toString());
    }

    @Test
    public void testStatusTrueString() {
        String s = "2022-03-04 03:30";
        LocalDateTime end = LocalDateTime.parse(s, Duke.getFormatter());
        Deadline d = new Deadline("anything", false, false, end);
        assertEquals("DEADLINE / 0 / 0 / anything / " + s, d.formatStringForFile());
    }

    @Test
    public void testStatusFalseString() {
        String s = "2021-04-04 02:55";
        LocalDateTime end = LocalDateTime.parse(s, Duke.getFormatter());
        Deadline d = new Deadline("reunion dinner prep", true, false, end);
        assertEquals("DEADLINE / 1 / 0 / reunion dinner prep / " + s, d.formatStringForFile());

    }
}
