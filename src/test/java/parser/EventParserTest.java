package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import command.Command;
import command.EventCommand;

public class EventParserTest {
    @Test
    public void shouldParseCorrectly() {
        Command actual = new EventParser().parse("demo /from 2023-01-01 /to 2024-01-01");
        Command expected = new EventCommand("demo", LocalDate.parse("2023-01-01"), LocalDate.parse("2024-01-01"));
        assertEquals(actual, expected);
    }
}
