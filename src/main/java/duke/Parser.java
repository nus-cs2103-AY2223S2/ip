package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

/**
 * A parser that parses all the user text based commands into the respective
 * commands to be executed.
 */
public class Parser {
    /**
     * Checks on whether the command is recognised. If it is, a corresponding
     * command is returned. Otherwise, an exception is throw to inform them that
     * the command does not exit.
     *
     * @param fullCommand The full string test command that the user input
     * @return The respective command that the user called for
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] inputSplit = fullCommand.split(" ", 2);
        switch (inputSplit[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            checkInput(inputSplit, ErrorMsg.MARK);
            return new MarkCommand(inputSplit[1]);
        case "unmark":
            checkInput(inputSplit, ErrorMsg.UNMARK);
            return new UnmarkCommand(inputSplit[1]);
        case "todo":
            checkInput(inputSplit, ErrorMsg.TODO);
            return new AddTodoCommand(inputSplit[1]);
        case "deadline":
            checkInput(inputSplit, ErrorMsg.DEADLINE);
            return new AddDeadlineCommand(inputSplit[1]);
        case "event":
            checkInput(inputSplit, ErrorMsg.EVENT);
            return new AddEventCommand(inputSplit[1]);
        case "delete":
            checkInput(inputSplit, ErrorMsg.DELETE);
            return new DeleteCommand(inputSplit[1]);
        case "find":
            checkInput(inputSplit, ErrorMsg.FIND);
            return new FindCommand(inputSplit[1]);
        default:
            throw new DukeException(ErrorMsg.DEFAULT.getText());
        }
    }

    private static void checkInput(String[] inputSplit, ErrorMsg errorMsg)
            throws DukeException {
        if (inputSplit.length < 2) {
            throw new DukeException(errorMsg.getText());
        }

        assert inputSplit.length == 2;
    }

    private enum ErrorMsg {
        MARK("Mark command missing list numbering."),
        UNMARK("Unmark command missing list numbering."),
        TODO("Todo command missing DESCRIPTION."),
        DEADLINE("Deadline command missing DESCRIPTION."),
        EVENT("Event command missing DESCRIPTION."),
        DELETE("Delete command missing list numbering."),
        FIND("Find command missing terms."),
        DEFAULT("Sorry but I don't understand what this means.");

        private final String text;

        ErrorMsg(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
