package cbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    private static final Event e1 = new Event("test 1", LocalDateTime.MIN, LocalDateTime.MAX);
    private static final Event e2 = new Event("test 2", LocalDateTime.parse("0023-01-27T01:00"),
            LocalDateTime.parse("0023-01-27T09:00"), true);

    @Test
    public void testGetSymbol() {
        assertEquals(e1.getSymbol(), "E");
    }

    @Test
    public void testHasTime() {
        assertTrue(e1.hasTime());
    }

    @Test
    public void testGetTime1() {
        assertEquals(e1.getTime(), LocalDateTime.MIN);
    }

    @Test
    public void testGetTime2() {
        assertEquals(e2.getTime(), LocalDateTime.parse("0023-01-27T01:00"));
    }

    @Test
    public void testToString1() {
        assertEquals(e1.toString(),
                "[E][ ] test 1 (01/01/00, 0000 - 31/12/99, 2359)");
    }

    @Test
    public void testToString2() {
        assertEquals(e2.toString(),
                "[E][X] test 2 (27/01/23, 0100 - 27/01/23, 0900)");
    }

    @Test
    public void testMakeFileFriendly1() {
        assertEquals(e1.makeFileFriendly(),
                "E ;;   ;; test 1 ;; -999999999-01-01T00:00 ;; +999999999-12-31T23:59:59.999999999");
    }

    @Test
    public void testMakeFileFriendly2() {
        assertEquals(e2.makeFileFriendly(),
                "E ;; X ;; test 2 ;; 0023-01-27T01:00 ;; 0023-01-27T09:00");
    }

    @Test
    public void testCompareTo() {
        assertTrue(e1.compareTo(e2) < 0);
    }
}
