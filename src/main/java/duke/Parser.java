package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.ThroughCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;

public class Parser {
    private enum Word {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, THROUGH
    }
    public static Command parse(String input) throws DukeException {
        String[] details = input.split(" ", 2);
        Word w = Word.valueOf(details[0].toUpperCase());

        switch (w) {
        case BYE:
            return new ByeCommand();

        case LIST:
            return new ListCommand();

        case MARK:
            if (details.length < 2) {
                throw new DukeException("Please input a number.");
            }
            return new MarkCommand(details[1]);

        case UNMARK:
            if (details.length < 2) {
                throw new DukeException("Please input a number.");
            }
            return new UnmarkCommand(details[1]);

        case TODO:
            if (details.length < 2) {
                throw new DukeException("Please input the necessary details.");
            }
            return new TodoCommand(details[1]);

        case DEADLINE:
            if (details.length < 2) {
                throw new DukeException("Please input the necessary details.");
            }
            return new DeadlineCommand(details[1]);

        case EVENT:
            if (details.length < 2) {
                throw new DukeException("Please input the necessary details.");
            }
            return new EventCommand(details[1]);

        case DELETE:
            if (details.length < 2) {
                throw new DukeException("Please input a number.");
            }
            return new DeleteCommand(details[1]);

        case THROUGH:
            if (details.length < 2) {
                throw new DukeException("Please input a date.");
            }
            return new ThroughCommand(details[1]);

        default:
            throw new DukeException("Unrecognised command. Try again.");
        }
    }
}
