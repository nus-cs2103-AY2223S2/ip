package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Deals with making sense of the user command
 */
public class Parser {

    protected enum COMMAND {BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND}

    public Parser() {

    }

    public static Command parse(String fullCommand) throws DukeException {
        try {
            String[] input = fullCommand.trim().split(" ", 2);
            COMMAND cmd = COMMAND.valueOf(input[0].toUpperCase());

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
                throw new DukeException("I'm sorry, but I don't know what that means");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means");
        }
    }
}
