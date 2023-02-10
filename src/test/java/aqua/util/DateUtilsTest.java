package aqua.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import aqua.exception.SyntaxException;

public class DateUtilsTest {
    @Test
    public void parse_defaultFormat_goodDate() {
        try {
            LocalDateTime actual = DateUtils.parse("2023-03-05T04:55");
            LocalDateTime expected = LocalDateTime.parse("2023-03-05T04:55");
            assertEquals(expected, actual);
        } catch (SyntaxException syntaxEx) {
            fail(syntaxEx);
        }
    }


    @Test
    public void parse_simplifiedFormat_goodDate() {
        try {
            LocalDateTime actual = DateUtils.parse("2023-03-05 0455");
            LocalDateTime expected = LocalDateTime.parse("2023-03-05T04:55");
            assertEquals(expected, actual);
        } catch (SyntaxException syntaxEx) {
            fail(syntaxEx);
        }
    }


    @Test
    public void parse_simplifiedFormatNoTime_goodDate() {
        try {
            LocalDateTime actual = DateUtils.parse("2023-03-05");
            LocalDateTime expected = LocalDateTime.parse("2023-03-05T00:00");
            assertEquals(expected, actual);
        } catch (SyntaxException syntaxEx) {
            fail(syntaxEx);
        }
    }


    @Test
    public void parse_invalidFormat_exceptionThrown() {
        try {
            DateUtils.parse("random day");
            fail("No exception thrown");
        } catch (SyntaxException syntaxEx) {
            assertEquals("I do not understand when this is [random day]", syntaxEx.getMessage());
        }
    }


    @Test
    public void parse_invalidNumbers_exceptionThrown() {
        try {
            DateUtils.parse("0000-00-00 0000");
            fail("No exception thrown");
        } catch (SyntaxException syntaxEx) {
            assertEquals("I do not understand when this is [0000-00-00 0000]", syntaxEx.getMessage());
        }
    }


    @Test
    public void isIntersectingNormal_sameTime_true() {
        LocalDateTime s1 = LocalDateTime.of(2023, 3, 5, 4, 55);
        LocalDateTime e1 = s1.plus(1, ChronoUnit.HOURS);
        LocalDateTime s2 = s1;
        LocalDateTime e2 = e1;
        assertEquals(true, DateUtils.isIntersecting(s1, e1, s2, e2));
    }


    @Test
    public void isIntersectingNormal_portionBack_true() {
        LocalDateTime s1 = LocalDateTime.of(2023, 3, 5, 4, 55);
        LocalDateTime e1 = s1.plus(1, ChronoUnit.HOURS);
        LocalDateTime s2 = s1.plus(30, ChronoUnit.MINUTES);
        LocalDateTime e2 = e1.plus(30, ChronoUnit.MINUTES);
        assertEquals(true, DateUtils.isIntersecting(s1, e1, s2, e2));
    }


    @Test
    public void isIntersectingNormal_portionFront_true() {
        LocalDateTime s1 = LocalDateTime.of(2023, 3, 5, 4, 55);
        LocalDateTime e1 = s1.plus(1, ChronoUnit.HOURS);
        LocalDateTime s2 = s1.minus(30, ChronoUnit.MINUTES);
        LocalDateTime e2 = e1.minus(30, ChronoUnit.MINUTES);
        assertEquals(true, DateUtils.isIntersecting(s1, e1, s2, e2));
    }


    @Test
    public void isIntersectingNormal_cover_true() {
        LocalDateTime s1 = LocalDateTime.of(2023, 3, 5, 4, 55);
        LocalDateTime e1 = s1.plus(1, ChronoUnit.HOURS);
        LocalDateTime s2 = s1.minus(30, ChronoUnit.MINUTES);
        LocalDateTime e2 = e1.plus(30, ChronoUnit.MINUTES);
        assertEquals(true, DateUtils.isIntersecting(s1, e1, s2, e2));
    }


    @Test
    void isIntersectingNormal_separate_false() {
        LocalDateTime s1 = LocalDateTime.of(2023, 3, 5, 4, 55);
        LocalDateTime e1 = s1.plus(1, ChronoUnit.HOURS);
        LocalDateTime s2 = s1.plus(120, ChronoUnit.MINUTES);
        LocalDateTime e2 = e1.plus(120, ChronoUnit.MINUTES);
        assertEquals(false, DateUtils.isIntersecting(s1, e1, s2, e2));
    }


    @Test
    void isIntersectingNormal_backToBack_false() {
        LocalDateTime s1 = LocalDateTime.of(2023, 3, 5, 4, 55);
        LocalDateTime e1 = s1.plus(1, ChronoUnit.HOURS);
        LocalDateTime s2 = e1;
        LocalDateTime e2 = e1.plus(120, ChronoUnit.MINUTES);
        assertEquals(false, DateUtils.isIntersecting(s1, e1, s2, e2));
    }


    @Test
    public void isIntersectingThreshold_sameTime_true() {
        double threshold = 30;
        LocalDateTime s1 = LocalDateTime.of(2023, 3, 5, 4, 55);
        LocalDateTime e1 = s1.plus(1, ChronoUnit.HOURS);
        LocalDateTime s2 = s1;
        LocalDateTime e2 = e1;
        assertEquals(true, DateUtils.isIntersecting(s1, e1, s2, e2, threshold));
    }


    @Test
    public void isIntersectingThreshold_portionBack_true() {
        double threshold = 30;
        LocalDateTime s1 = LocalDateTime.of(2023, 3, 5, 4, 55);
        LocalDateTime e1 = s1.plus(1, ChronoUnit.HOURS);
        LocalDateTime s2 = s1.plus(30, ChronoUnit.MINUTES);
        LocalDateTime e2 = e1.plus(30, ChronoUnit.MINUTES);
        assertEquals(true, DateUtils.isIntersecting(s1, e1, s2, e2, threshold));
    }


    @Test
    public void isIntersectingThreshold_portionFront_true() {
        double threshold = 30;
        LocalDateTime s1 = LocalDateTime.of(2023, 3, 5, 4, 55);
        LocalDateTime e1 = s1.plus(1, ChronoUnit.HOURS);
        LocalDateTime s2 = s1.minus(30, ChronoUnit.MINUTES);
        LocalDateTime e2 = e1.minus(30, ChronoUnit.MINUTES);
        assertEquals(true, DateUtils.isIntersecting(s1, e1, s2, e2, threshold));
    }


    @Test
    public void isIntersectingThreshold_cover_true() {
        double threshold = 30;
        LocalDateTime s1 = LocalDateTime.of(2023, 3, 5, 4, 55);
        LocalDateTime e1 = s1.plus(1, ChronoUnit.HOURS);
        LocalDateTime s2 = s1.minus(30, ChronoUnit.MINUTES);
        LocalDateTime e2 = e1.plus(30, ChronoUnit.MINUTES);
        assertEquals(true, DateUtils.isIntersecting(s1, e1, s2, e2, threshold));
    }


    @Test
    public void isIntersectingThreshold_separateThreshold_true() {
        double threshold = 30;
        LocalDateTime s1 = LocalDateTime.of(2023, 3, 5, 4, 55);
        LocalDateTime e1 = s1.plus(1, ChronoUnit.HOURS);
        LocalDateTime s2 = e1.plus(5, ChronoUnit.MINUTES);
        LocalDateTime e2 = e1.plus(10, ChronoUnit.MINUTES);
        assertEquals(true, DateUtils.isIntersecting(s1, e1, s2, e2, threshold));
    }


    @Test
    public void isIntersectingThreshold_backToBackThreshold_true() {
        double threshold = 30;
        LocalDateTime s1 = LocalDateTime.of(2023, 3, 5, 4, 55);
        LocalDateTime e1 = s1.plus(1, ChronoUnit.HOURS);
        LocalDateTime s2 = e1;
        LocalDateTime e2 = e1.plus(10, ChronoUnit.MINUTES);
        assertEquals(true, DateUtils.isIntersecting(s1, e1, s2, e2, threshold));
    }


    @Test
    public void isIntersectingThreshold_separate_false() {
        double threshold = 30;
        LocalDateTime s1 = LocalDateTime.of(2023, 3, 5, 4, 55);
        LocalDateTime e1 = s1.plus(1, ChronoUnit.HOURS);
        LocalDateTime s2 = s1.plus(120, ChronoUnit.MINUTES);
        LocalDateTime e2 = e1.plus(120, ChronoUnit.MINUTES);
        assertEquals(false, DateUtils.isIntersecting(s1, e1, s2, e2, threshold));
    }


    @Test
    public void isIntersectingThreshold_backToBack_false() {
        double threshold = 30;
        LocalDateTime s1 = LocalDateTime.of(2023, 3, 5, 4, 55);
        LocalDateTime e1 = s1.plus(1, ChronoUnit.HOURS);
        LocalDateTime s2 = e1;
        LocalDateTime e2 = e1.plus(120, ChronoUnit.MINUTES);
        assertEquals(false, DateUtils.isIntersecting(s1, e1, s2, e2, threshold));
    }


    @Test
    public void toStartOfWeek_startOfWeek_startOfWeek() {
        LocalDateTime expected = LocalDateTime.of(2023, 2, 6, 0, 0);
        LocalDateTime given = expected;
        DayOfWeek weekStart = DayOfWeek.MONDAY;
        LocalDateTime actual = DateUtils.toStartOfWeek(given, weekStart);
        assertEquals(expected, actual);
    }


    @Test
    public void toStartOfWeek_midWeek_startOfWeek() {
        LocalDateTime expected = LocalDateTime.of(2023, 2, 6, 0, 0);
        LocalDateTime given = expected.plus(3, ChronoUnit.DAYS);
        DayOfWeek weekStart = DayOfWeek.MONDAY;
        LocalDateTime actual = DateUtils.toStartOfWeek(given, weekStart);
        assertEquals(expected, actual);
    }


    @Test
    public void toStartOfDay_startOfDay_startOfDay() {
        LocalDateTime expected = LocalDateTime.of(2023, 2, 6, 0, 0);
        LocalDateTime given = expected;
        LocalDateTime actual = DateUtils.toStartOfDay(given);
        assertEquals(expected, actual);
    }


    @Test
    public void toStartOfDay_midDay_startOfDay() {
        LocalDateTime expected = LocalDateTime.of(2023, 2, 6, 0, 0);
        LocalDateTime given = expected.plus(12, ChronoUnit.HOURS);
        LocalDateTime actual = DateUtils.toStartOfDay(given);
        assertEquals(expected, actual);
    }
}
