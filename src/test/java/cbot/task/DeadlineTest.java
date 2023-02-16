package cbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private static final Deadline dl1 = new Deadline("test 1", LocalDateTime.MIN);
    private static final Deadline dl2 = new Deadline("test 2", LocalDateTime.parse("0023-01-27T10:00"), true);

    @Test
    public void testGetSymbol() {
        assertEquals(dl1.getSymbol(), "D");
    }

    @Test
    public void testHasTime() {
        assertTrue(dl1.hasTime());
    }

    @Test
    public void testGetTime1() {
        assertEquals(dl1.getTime(), LocalDateTime.MIN);
    }

    @Test
    public void testGetTime2() {
        assertEquals(dl2.getTime(), LocalDateTime.parse("0023-01-27T10:00"));
    }

    @Test
    public void testToString1() {
        assertEquals(dl1.toString(),
                "[D][ ] test 1 (< 01/01/00, 0000)");
    }

    @Test
    public void testToString2() {
        assertEquals(dl2.toString(),
                "[D][X] test 2 (< 27/01/23, 1000)");
    }

    @Test
    public void testMakeFileFriendly1() {
        assertEquals(dl1.makeFileFriendly(),
                "D ;;   ;; test 1 ;; -999999999-01-01T00:00");
    }

    @Test
    public void testMakeFileFriendly2() {
        assertEquals(dl2.makeFileFriendly(),
                "D ;; X ;; test 2 ;; 0023-01-27T10:00");
    }

    @Test
    public void testCompareTo() {
        assertTrue(dl1.compareTo(dl2) < 0);
    }
}
