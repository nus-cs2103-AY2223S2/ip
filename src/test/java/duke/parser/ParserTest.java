package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.Command;
import duke.exception.DukeException;
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
        } catch (DateTimeParseException | DukeException e) {
            e.printStackTrace();
        }
    }
}
