package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.ThroughCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.commands.UpdateCommand;

/**
 * A parser that will parse the input String from the user into
 * the relevant Duke Commands with the necessary information required
 *
 * @author Cheam Jia Wei
 */
public class Parser {
    private enum Word {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, THROUGH, FIND, UPDATE
    }

    /**
     * Parse function that will interpret the input by the user into
     * the relevant Duke command.
     *
     * @param input String that was input by the user and picked up by the scanner
     * @return A Duke Command that will be executed by the program
     * @throws DukeException Throws exception when an invalid input in given
     */
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

        case FIND:
            if (details.length < 2) {
                throw new DukeException("Please input the necessary details.");
            }
            return new FindCommand(details[1]);

        case UPDATE:
            if (details.length < 2) {
                throw new DukeException("Please input the necessary details.");
            }
            return new UpdateCommand(details[1]);

        default:
            assert false : w;
            throw new DukeException("Unrecognised command. Try again.");
        }
    }
}
