package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import command.Command;
import command.DeadlineCommand;

public class DeadlineParserTest {
    @Test
    public void shouldParseCorrectly() {
        Command actual = new DeadlineParser().parse("demo /by 2023-01-01");
        Command expected = new DeadlineCommand("demo", LocalDate.parse("2023-01-01"));
        assertEquals(actual, expected);
    }
}
