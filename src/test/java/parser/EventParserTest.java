package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import command.Command;
import command.EventCommand;
import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

public class EventParserTest {
    @Test
    public void shouldParseCorrectly() {
        Command actual = new EventParser().parse("demo /from 2023-01-01 /to 2024-01-01");
        Command expected = new EventCommand("demo", LocalDate.parse("2023-01-01"), LocalDate.parse("2024-01-01"));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAcceptEmptyTask() {
        assertThrows(MissingArgumentException.class, () ->
                new EventParser().parse(" /from 2023-01-01 /to 2024-01-01"));
    }

    @Test
    public void shouldNotAcceptMissingStartDate() {
        assertThrows(MissingArgumentException.class, () ->
                new EventParser().parse("demo /to 2024-01-01"));
    }

    @Test
    public void shouldNotAcceptEmptyStartDate() {
        assertThrows(MissingArgumentException.class, () ->
                new EventParser().parse("demo /from  /to 2024-01-01"));
    }

    @Test
    public void shouldNotAcceptWrongStartDateFormat() {
        assertThrows(InvalidArgumentException.class, () ->
                new EventParser().parse("demo /from 01 Jan 2023 /to 2024-01-01"));
    }

    @Test
    public void shouldNotAcceptMissingEndDate() {
        assertThrows(MissingArgumentException.class, () ->
                new EventParser().parse("demo /from 2023-01-01"));
    }

    @Test
    public void shouldNotAcceptEmptyEndDate() {
        assertThrows(MissingArgumentException.class, () ->
                new EventParser().parse("demo /from 2023-01-01 /to "));
    }

    @Test
    public void shouldNotAcceptWrongEndDateFormat() {
        assertThrows(InvalidArgumentException.class, () ->
                new EventParser().parse("demo /from 2023-01-01 /to 01 Jan 2024"));
    }
}
