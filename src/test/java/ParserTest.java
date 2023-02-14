package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;

public class ParserTest {
    @Test
    public void correctCommand() {
        try {
            Command todo = Parser.parse("todo run");
            assertTrue(todo instanceof AddTodoCommand);

            Command deadline = Parser.parse("deadline homework /by:19 Dec 2023 20:00");
            assertTrue(deadline instanceof AddDeadlineCommand);

            Command event = Parser.parse("event meeting /from:19 Dec 2023 20:00 /to:19 Dec 2023 20:30");
            assertTrue(event instanceof AddEventCommand);
        } catch (duke.DukeException e) {
            System.out.println(e.toString());
        }

    }
}
