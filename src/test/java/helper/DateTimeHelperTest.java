package helper;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeHelperTest {
    @Test
    public void testParse(){
        try {
            assertEquals(DateTimeHelper.parse("2/12/2012 1200"), LocalDateTime.of(2012, 12, 2, 12, 0));
        } catch (Exception e) {
            System.out.println("Cannot parse.");
        }
    }

    @Test
    public void testParseFormattedDateTime() {
        try {
            assertEquals(DateTimeHelper.parseFormattedDateTime("Oct 12 2022 13:33"), LocalDateTime.of(2022, 10, 12, 13, 33));
        } catch (Exception e) {
            System.out.println("Cannot parse.");
        }
    }

    @Test
    public void testStringify() {
        try {
            assertEquals("Oct 12 2022 13:33", DateTimeHelper.stringify(LocalDateTime.of(2022, 10, 12, 13, 33)));
        } catch (Exception e) {
            System.out.println("Cannot stringify.");
        }
    }
}