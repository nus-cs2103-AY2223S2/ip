package duke;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.functions.Parser;
import duke.tasks.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class ParserTest {
    @Test
    public void parseTask_wrongType_exceptionThrown() {
        try {
            String input = "delete abc";
            TaskList dl = new TaskList();
            Parser p = new Parser(dl);
            p.handleInput(input);
        } catch(DukeException e) {
            assertEquals("Please input a number after the command.", e.getMessage());
        }
    }

    @Test
    public void parseUserResponse_unknownCommand_exceptionThrown() {
        try {
            TaskList dl = new TaskList();
            Parser p = new Parser(dl);
            p.handleInput("boop");
        } catch(DukeException e) {
            assertEquals(
                    "Sorry, I don't know what that line means. You could try typing from our list of commands:\n" +
                    "[todo, deadline, event, mark, unmark, list, bye, delete]", e.getMessage());
        }
    }
}