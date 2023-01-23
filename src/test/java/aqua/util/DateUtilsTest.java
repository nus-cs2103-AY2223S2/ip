package aqua.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import aqua.exception.IllegalSyntaxException;

public class DateUtilsTest {
    @Test
    public void parse_defaultFormat_goodDate() {
        try {
            LocalDateTime actual = DateUtils.parse("2023-03-05T04:55");
            LocalDateTime expected = LocalDateTime.parse("2023-03-05T04:55");
            assertEquals(expected, actual);
        } catch (IllegalSyntaxException syntaxEx) {
            fail(syntaxEx);
        }
    }


    @Test
    public void parse_simplifiedFormat_goodDate() {
        try {
            LocalDateTime actual = DateUtils.parse("2023-03-05 0455");
            LocalDateTime expected = LocalDateTime.parse("2023-03-05T04:55");
            assertEquals(expected, actual);
        } catch (IllegalSyntaxException syntaxEx) {
            fail(syntaxEx);
        }
    }


    @Test
    public void parse_simplifiedFormatNoTime_goodDate() {
        try {
            LocalDateTime actual = DateUtils.parse("2023-03-05");
            LocalDateTime expected = LocalDateTime.parse("2023-03-05T00:00");
            assertEquals(expected, actual);
        } catch (IllegalSyntaxException syntaxEx) {
            fail(syntaxEx);
        }
    }


    @Test
    public void parse_invalidFormat__exceptionThrown() {
        try {
            DateUtils.parse("random day");
            fail("No exception thrown");
        } catch (IllegalSyntaxException syntaxEx) {
            assertEquals("I do not understand when this is [random day]", syntaxEx.getMessage());
        }
    }


    @Test
    public void parse_invalidNumbers_exceptionThrown() {
        try {
            DateUtils.parse("0000-00-00 0000");
            fail("No exception thrown");
        } catch (IllegalSyntaxException syntaxEx) {
            assertEquals("I do not understand when this is [0000-00-00 0000]", syntaxEx.getMessage());
        }
    }
}
