package cbot.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TimeStuffTest {
    private static final LocalDateTime one = LocalDateTime.parse("2001-01-01T00:00");

    @Test
    public void testParseDT0() {
        assertEquals(TimeStuff.textToDT("2001-01-01"), one);
    }

    @Test
    public void testParseDT00() {
        assertEquals(TimeStuff.textToDT("2001-01-01 0000"), one);
    }

    @Test
    public void testParseDT01() {
        assertEquals(TimeStuff.textToDT("2001-01-01 00:00"), one);
    }

    @Test
    public void testParseDT02() {
        assertEquals(TimeStuff.textToDT("2001-01-01 12am"), one);
    }

    @Test
    public void testParseDT03() {
        assertEquals(TimeStuff.textToDT("2001-01-01 12 am"), one);
    }

    @Test
    public void testParseDT04() {
        assertEquals(TimeStuff.textToDT("2001-01-01 12.00am"), one);
    }

    @Test
    public void testParseDT05() {
        assertEquals(TimeStuff.textToDT("2001-01-01 12.00 am"), one);
    }

    @Test
    public void testParseDT06() {
        assertEquals(TimeStuff.textToDT("2001-01-01 12:00am"), one);
    }

    @Test
    public void testParseDT07() {
        assertEquals(TimeStuff.textToDT("2001-01-01 12:00 am"), one);
    }

    //

    @Test
    public void testParseDT1() {
        // interesting, can't do 1/1/1
        assertEquals(TimeStuff.textToDT("1/1/2001"), one);
    }

    @Test
    public void testParseDT2() {
        assertEquals(TimeStuff.textToDT("1 Jan 2001"), one);
    }

    @Test
    public void testParseDT3() {
        assertEquals(TimeStuff.textToDT("Jan 1 2001"), one);
    }

    @Test
    public void testText() {
        assertEquals(TimeStuff.dtToText(one), "01/01/01, 0000");
    }
}
