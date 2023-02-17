package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TimeConvertorTest {
    @Test
    public void timeTest1() {
        TimeConvertor timeTest = new TimeConvertor("2015-03-17 09:00");
        assertEquals("2015-03-17", timeTest.getDate());
    }

    @Test
    public void timeTest2() {
        TimeConvertor timeTest = new TimeConvertor("2015-03-17 09:00");
        assertEquals("Mar 17 2015", timeTest.getDate("MMM dd yyyy"));
    }
}
