package duke.ui;

import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    @Test
    public void parse_emptyInput_exceptionThrown() {
        Throwable exception = assertThrows(DukeException.class, () -> parser.parse(""));
        assertEquals("Invalid command", exception.getMessage());
    }

    @Test
    public void parse_nullInput_exceptionThrown() {
        Throwable exception = assertThrows(DukeException.class, () -> parser.parse(null));
        assertEquals("Null exception encountered", exception.getMessage());
    }

    @Test
    public void parse_byeInput_exitCommandReturned() throws DukeException {
        assertAll("exit commands",
                () -> assertEquals(ExitCommand.class, parser.parse("bye").getClass()),
                () -> assertEquals(ExitCommand.class, parser.parse("bYe").getClass()),
                () -> assertEquals(ExitCommand.class, parser.parse("bye 123").getClass())
        );    }

    @Test
    public void parse_listInput_listCommandReturned() throws DukeException {
        assertAll("list commands",
                () -> assertEquals(ListCommand.class, parser.parse("list").getClass()),
                () -> assertEquals(ListCommand.class, parser.parse("lIsT").getClass()),
                () -> assertEquals(ListCommand.class, parser.parse("list 123").getClass())
        );
    }

}