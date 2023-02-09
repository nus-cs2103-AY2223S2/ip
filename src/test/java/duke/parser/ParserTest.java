package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.FindFreeTimeCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MassDeleteCommand;
import duke.command.ReminderCommand;
import duke.command.SortCommand;
import duke.exception.DukeException;

class ParserTest {
    @Test
    public void parse_inputIsBye_returnsExitCommand() throws DukeException {
        String input = "bye";
        Command command = Parser.parse(input);
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parse_inputIsList_returnsListCommand() throws DukeException {
        String input = "list";
        Command command = Parser.parse(input);
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parse_inputIsReminder_returnsReminderCommand() throws DukeException {
        String input = "reminder";
        Command command = Parser.parse(input);
        assertTrue(command instanceof ReminderCommand);
    }

    @Test
    public void parse_inputIsMassdelete_returnsMassDeleteCommand() throws DukeException {
        String input = "massdelete";
        Command command = Parser.parse(input);
        assertTrue(command instanceof MassDeleteCommand);
    }

    @Test
    public void parse_inputIsFree_returnsFindFreeTimeCommand() throws DukeException {
        String input = "free";
        Command command = Parser.parse(input);
        assertTrue(command instanceof FindFreeTimeCommand);
    }

    @Test
    public void parse_inputIsSortByDate_returnsSortCommand() throws DukeException {
        String input = "sort by date";
        Command command = Parser.parse(input);
        assertTrue(command instanceof SortCommand);

    }

    @Test
    public void parse_inputIsHelp_returnsHelpCommand() throws DukeException {
        String input = "help";
        Command command = Parser.parse(input);
        assertTrue(command instanceof HelpCommand);
    }

    @Test
    public void parse_inputIsUnknownCommand_throwsInvalidInputException() {
        String input = "unknownCommand";
        try {
            Command command = Parser.parse(input);
            fail("Expected InvalidInputException to be thrown.");
        } catch (DukeException e) {
            assertEquals("There is something wrong with the input ...\n"
                    + ErrorMessage.UNRECOGNIZED_ERROR, e.getMessage());
        }

    }

    @Test
    public void parse_inputIsEmpty_throwsInvalidInputException() {
        String input = "";
        try {
            Command command = Parser.parse(input);
            fail("Expected InvalidInputException to be thrown.");
        } catch (DukeException e) {
            assertEquals("There is something wrong with the input ...\n"
                    + ErrorMessage.EMPTY_ERROR, e.getMessage());
        }
    }
}
