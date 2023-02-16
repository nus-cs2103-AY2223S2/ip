package boo.datetime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void getDateTimeObject_validString_obtainTemporalObject() {
        assertEquals(LocalDate.of(2023, 2, 2),
                    DateTime.getDateTimeObject("2023-02-02"));
        assertEquals(LocalDateTime.of(2023, 2, 2, 14, 30),
                DateTime.getDateTimeObject("2023-02-02 14:30"));

    }
    @Test
    public void getDateTimeObject_invalidString_exceptionThrown() {
        try {
            assertEquals(LocalDate.of(2023, 2, 2),
                    DateTime.getDateTimeObject("02 Feb 2023"));
            //Should not reach here
        } catch (DateTimeParseException e) {
            assertEquals(1, 1); //Should reach here
        }
    }
}
