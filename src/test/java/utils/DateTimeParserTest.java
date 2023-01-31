package utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import membot.utils.DateTimeParser;

public class DateTimeParserTest {
    @Test
    public void parseTest() {
        assertThrows(DateTimeException.class, () -> DateTimeParser.parse("some random date"));
        assertThrows(DateTimeException.class, () -> DateTimeParser.parse("12/12/2022 25:00"));
        assertDoesNotThrow(() -> DateTimeParser.parse("12/12/2022 16:12"));
        assertDoesNotThrow(() -> DateTimeParser.parse("12/12/2022"));
        assertEquals(DateTimeParser.parse("12/12/2022 16:12"),
                LocalDateTime.of(2022, 12, 12, 16, 12));
        assertEquals(DateTimeParser.parse("12/12/2022"),
                LocalDateTime.of(2022, 12, 12, 0, 0));
    }

    @Test
    public void formatTest() {
        LocalDateTime dt = LocalDateTime.parse("01/01/2023 16:30",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        assertEquals(DateTimeParser.format(dt), "01/01/2023 16:30");

        LocalDateTime d = LocalDate.parse("01/02/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay();
        assertEquals(DateTimeParser.format(d), "01/02/2023 00:00");
    }
}
