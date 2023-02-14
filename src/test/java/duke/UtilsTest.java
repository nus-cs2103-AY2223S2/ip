package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static duke.Utils.parseDateTime;

public class UtilsTest {
    private static MockedStatic<LocalDateTime> dateTime;
    
    public static final LocalDateTime DEFAULT_TIME = LocalDateTime.of(2022, 01, 01, 12, 30);

    @BeforeAll
    public static void setup() {
        dateTime = Mockito.mockStatic(LocalDateTime.class);
        dateTime.when(LocalDateTime::now)
            .thenReturn(DEFAULT_TIME);
    }

    @AfterAll
    public static void teardown() {
        dateTime.close();
    }

    // @IndicativeSentencesGeneration(
    //   separator = " ",
    //   generator = DisplayNameGenerator.ReplaceUnderscores.class
    // )
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    @Nested
    class ParseDateTime_should {
        @Test
        public void parse_12_hour_times_correctly() {
            assertEquals(parseDateTime("12:05am", null), LocalDateTime.of(2022, 1, 1, 0, 5));
            assertEquals(parseDateTime("09:10pm", null), LocalDateTime.of(2022, 1, 1, 21, 10));
            assertEquals(parseDateTime(null, "11:34am"), LocalDateTime.of(2022, 1, 1, 11, 34));
        }

        @Test
        public void parse_24_hour_times_correctly() {
            assertEquals(parseDateTime("23:00", null), LocalDateTime.of(2022, 1, 1, 23, 0));
            assertEquals(parseDateTime("2300", null), LocalDateTime.of(2022, 1, 1, 23, 0));
            assertEquals(parseDateTime("17:00", null), LocalDateTime.of(2022, 1, 1, 17, 0));
            assertEquals(parseDateTime("1233", null), LocalDateTime.of(2022, 1, 1, 12, 33));

            assertThrows(DateTimeParseException.class, () -> parseDateTime("2500", null));
            assertThrows(DateTimeParseException.class, () -> parseDateTime("1269", null));

            assertThrows(DateTimeParseException.class, () -> parseDateTime(null, "17:00am"), 
                "Should throw DateTimeParseException when encountering a 24 hour time with am or pm");

            assertThrows(DateTimeParseException.class, () -> parseDateTime(null, "23:00pm"),
                "Should throw DateTimeParseException when encountering a 24 hour time with am or pm");
        }

        @Test
        public void parse_dates_correctly() {
            assertEquals(parseDateTime("03/05", null), LocalDateTime.of(2022, 3, 5, 12, 30));
            assertEquals(parseDateTime(null, "31/12"), LocalDateTime.of(2022, 12, 31, 12, 30));
            assertThrows(DateTimeParseException.class, () -> parseDateTime(null, "12/31"),
                "Should throw DateTimeParseException when encountering the wrong date format");
        }

        @Test
        public void parse_both_correctly() {
            assertEquals(parseDateTime("07/11", "12:00am"), LocalDateTime.of(2022, 11, 7, 0, 0));
            assertEquals(parseDateTime("1639", "04/07"), LocalDateTime.of(2022, 7, 4, 16, 39));
            assertThrows(DateTimeParseException.class, () -> parseDateTime("1400", "1231"),
                "Should throw DateTimeParseException when there's no valid date");
            assertThrows(DateTimeParseException.class, () -> parseDateTime("03/02", "01/01"),
                "Should throw DateTimeParseException when there's no valid time");
        }
    }
}
