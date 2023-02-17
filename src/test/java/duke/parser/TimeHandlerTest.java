package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidInputException;

class TimeHandlerTest {
    @Test
    void parseToLocalDate_validDate_shouldReturnLocalDate() throws InvalidInputException {
        String date = "Jan 20 2021";
        LocalDate expected = LocalDate.of(2021, 1, 20);
        assertEquals(expected, TimeHandler.parseToLocalDate(date));
    }

    @Test
    void parseToLocalDate_invalidDate_shouldThrowInvalidInputException() {
        String date = "2021-13-40";
        assertThrows(InvalidInputException.class, () -> TimeHandler.parseToLocalDate(date));
    }

    @Test
    void parseToLocalDate_validDate2_shouldReturnLocalDate() throws InvalidInputException {
        String date = "Feb 28 2022";
        LocalDate expected = LocalDate.of(2022, 2, 28);
        assertEquals(expected, TimeHandler.parseToLocalDate(date));
    }

    @Test
    void parseToLocalDate_invalidDate2_shouldThrowInvalidInputException() {
        String date = "2022-0-29";
        assertThrows(InvalidInputException.class, () -> TimeHandler.parseToLocalDate(date));
    }

    @Test
    void parseToLocalDate_validDate3_shouldReturnLocalDate() throws InvalidInputException {
        String date = "Aprl 30 2021";
        LocalDate expected = LocalDate.of(2021, 4, 30);
        assertThrows(InvalidInputException.class, () -> TimeHandler.parseToLocalDate(date));
    }

    @Test
    void parseToLocalDate_invalidDate3_shouldThrowInvalidInputException() {
        String date = "021-04-31";
        assertThrows(InvalidInputException.class, () -> TimeHandler.parseToLocalDate(date));
    }

    @Test
    void parseToLocalDateTime_validDateTime_shouldReturnLocalDateTime() throws InvalidInputException {
        String date = "2021-01-20T09:30";
        LocalDateTime expected = LocalDateTime.of(2021, 1, 20, 9, 30);
        assertEquals(expected, TimeHandler.parseToLocalDateTime(date));
    }

    @Test
    void parseToLocalDateTime_invalidDateTime_shouldThrowInvalidInputException() {
        String date = "2021-1-20 09:30";
        assertThrows(InvalidInputException.class, () -> TimeHandler.parseToLocalDateTime(date));
    }

    @Test
    void parseToLocalDateTime_validDateTime2_shouldReturnLocalDateTime() throws InvalidInputException {
        String date = "2022-11-20T15:45";
        LocalDateTime expected = LocalDateTime.of(2022, 11, 20, 15, 45);
        assertEquals(expected, TimeHandler.parseToLocalDateTime(date));
    }

    @Test
    void parseToLocalDateTime_invalidDateTime2_shouldThrowInvalidInputException() {
        String date = "2022-11-20 15:454";
        assertThrows(InvalidInputException.class, () -> TimeHandler.parseToLocalDateTime(date));
    }

    @Test
    void parseToLocalDateTime_validDateTime3_shouldReturnLocalDateTime() throws InvalidInputException {
        String date = "2022-11-20T23:59";
        LocalDateTime expected = LocalDateTime.of(2022, 11, 20, 23, 59);
        assertEquals(expected, TimeHandler.parseToLocalDateTime(date));
    }

    @Test
    void parseToLocalDateTime_invalidDateTime3_shouldThrowInvalidInputException() {
        String date = "2022-11-20 124:00";
        assertThrows(InvalidInputException.class, () -> TimeHandler.parseToLocalDateTime(date));
    }

    @Test
    void humanReadableFormat_validDuration_shouldReturnHumanReadableFormat() {
        Duration duration = Duration.ofHours(2).plusMinutes(30);
        String expected = "2h 30m";
        assertEquals(expected, TimeHandler.humanReadableFormat(duration));
    }

    @Test
    void humanReadableFormat_validDuration2_shouldReturnHumanReadableFormat() {
        Duration duration = Duration.ofDays(3).plusHours(5).plusMinutes(45);
        String expected = "77h 45m";
        assertEquals(expected, TimeHandler.humanReadableFormat(duration));
    }

    @Test
    void humanReadableFormat_validDuration3_shouldReturnHumanReadableFormat() {
        Duration duration = Duration.ofMinutes(90);
        String expected = "1h 30m";
        assertEquals(expected, TimeHandler.humanReadableFormat(duration));
    }

    @Test
    void humanReadableFormat_validDuration4_shouldReturnHumanReadableFormat() {
        Duration duration = Duration.ofSeconds(3600);
        String expected = "1h";
        assertEquals(expected, TimeHandler.humanReadableFormat(duration));
    }

    @Test
    void humanReadableFormat_validDuration5_shouldReturnHumanReadableFormat() {
        Duration duration = Duration.ofMillis(120000);
        String expected = "2m";
        assertEquals(expected, TimeHandler.humanReadableFormat(duration));
    }

    @Test
    void humanReadableFormat_validDuration6_shouldReturnHumanReadableFormat() {
        Duration duration = Duration.ofMillis(60000);
        String expected = "1m";
        assertEquals(expected, TimeHandler.humanReadableFormat(duration));
    }

    @Test
    void humanReadableFormat_validDuration7_shouldReturnHumanReadableFormat() {
        Duration duration = Duration.ofMillis(10000);
        String expected = "10s";
        assertEquals(expected, TimeHandler.humanReadableFormat(duration));
    }

    @Test
    void humanReadableFormat_validDuration8_shouldReturnHumanReadableFormat() {
        Duration duration = Duration.ofMillis(1000);
        String expected = "1s";
        assertEquals(expected, TimeHandler.humanReadableFormat(duration));
    }
}
