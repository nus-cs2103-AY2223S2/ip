package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Deals with making sense of the user command
 */
public class Parser {

    protected enum CommandType { BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND }

    /**
     * Generates command from user command input
     *
     * @param fullCommand User command input
     * @throws DukeException If the input from the user is not a valid command
     */
    public static Command parse(String fullCommand) throws DukeException {
        try {
            CommandType cmd = CommandType.valueOf(fullCommand
                    .trim()
                    .split(" ", 2)[0]
                    .toUpperCase());

            switch (cmd) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case TODO:
                return new AddCommand(Todo.generate(fullCommand));
            case DEADLINE:
                return new AddCommand(Deadline.generate(fullCommand));
            case EVENT:
                return new AddCommand(Event.generate(fullCommand));
            case MARK:
                return new MarkCommand(fullCommand);
            case UNMARK:
                return new UnmarkCommand(fullCommand);
            case DELETE:
                return new DeleteCommand(fullCommand);
            case FIND:
                return new FindCommand(fullCommand);
            default:
                throw DukeException.getErrorParse();
            }
        } catch (IllegalArgumentException e) {
            throw DukeException.getErrorParse();
        }
    }
}
