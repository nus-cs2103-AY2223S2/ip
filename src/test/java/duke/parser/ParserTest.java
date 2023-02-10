package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.InvalidCommandArgsException;
import duke.exception.NoSuchCommandException;
import duke.task.Event;
import duke.task.Todo;

public class ParserTest {
    @Test
    void testParse() {
        try {
            Command cmd = Parser.parse("Todo test");
            Todo expected = new Todo("test", false);
            assertTrue(cmd instanceof AddCommand);
            assertEquals(expected.toString(), ((AddCommand) cmd).getTask().toString());

            cmd = Parser.parse("event test /from 01/01/2020 /to 02/01/2020");
            Event expected2 = new Event("test", false, LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                    LocalDateTime.of(2020, 1, 2, 0, 0, 0));
            assertTrue(cmd instanceof AddCommand);
            assertEquals(expected2.toString(), ((AddCommand) cmd).getTask().toString());

            assertThrows(InvalidCommandArgsException.class, () -> {
                Parser.parse("Todo");
                Parser.parse("Deadline");
                Parser.parse("Event");
            });

            assertThrows(InvalidCommandArgsException.class, () -> {
                Parser.parse("Deadline 1");
                Parser.parse("Event 1");
            });
        } catch (DateTimeParseException | DukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testParseCsv() {
        try {
            AddCommand cmd = Parser.parseCsv("T,1,test,,");
            Todo expected = new Todo("test", true);
            assertEquals(expected.toString(), cmd.getTask().toString());

            assertThrows(NoSuchCommandException.class, () -> {
                Parser.parseCsv("X,1,test,,");
            });
        } catch (DateTimeParseException | DukeException e) {
            e.printStackTrace();
        }
    }
}
