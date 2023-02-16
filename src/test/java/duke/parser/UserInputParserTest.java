package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;


class UserInputParserTest {

    @Test
    void parse_validTodoInput_success() throws DukeException {
        AddCommand addTodoCommand = new AddCommand(new Todo("Study CS2103"));
        assertEquals(addTodoCommand.toString(), UserInputParser.parse("tODo Study CS2103").toString());
    }

    @Test
    void parse_invalidTodoInput_failure() {
        AddCommand addTodoCommand = new AddCommand(new Todo("Study CS2103"));
        try {
            assertEquals(addTodoCommand.toString(), UserInputParser.parse("t0Do Study CS2103").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but Fake Duke doesn't know what that means :-(\n", e.getMessage());
        }
    }

    @Test
    void parse_validDeadlineInput_success() throws DukeException {
        AddCommand addDeadlineCommand = new AddCommand(new Deadline("Study CS2103 /by 2023-05-01 12:00"));
        assertEquals(addDeadlineCommand.toString(),
                UserInputParser.parse("dEadline Study CS2103 /by 2023-05-01 12:00").toString());
    }

    @Test
    void parse_invalidDeadlineInput_failure() {
        try {
            AddCommand addDeadlineCommand = new AddCommand(new Deadline("Study CS2103 /by 2023-05-01 12:00"));
            assertEquals(addDeadlineCommand.toString(),
                    UserInputParser.parse("dEadl1ne Study CS2103 /by 2023-05-01 12:00").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but Fake Duke doesn't know what that means :-(\n", e.getMessage());
        }
    }

    @Test
    void parse_validEventInput_success() throws DukeException {
        AddCommand addEventCommand = new AddCommand(
                new Event("Holiday /from 2023-05-01 12:00 /to 2023-06-01 12:00"));
        assertEquals(addEventCommand.toString(),
                UserInputParser.parse("eVenT Holiday /from 2023-05-01 12:00 /to 2023-06-01 12:00").toString());
    }

    @Test
    void parse_invalidEventInput_failure() {
        try {
            AddCommand addEventCommand = new AddCommand(
                    new Event("Holiday /from 2023-05-01 12:00 /to 2023-06-01 12:00"));
            assertEquals(addEventCommand.toString(),
                    UserInputParser.parse("eV3nT Holiday /from 2023-05-01 12:00 /to 2023-06-01 12:00").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but Fake Duke doesn't know what that means :-(\n", e.getMessage());
        }
    }
}
