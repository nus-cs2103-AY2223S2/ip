package duke.parser;

import duke.Deadline;
import duke.Event;
import duke.Todo;
import duke.command.*;
import duke.exception.DukeException;

public class Parser {
    /**
     * Represents Duke's parser to parse all userInputs and validate them before performing appropriate actions.
     */
    public enum ValidCommands {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE, FIND, UPDATE
    }

    /**
     * Parse the userInput/commmand and returns the appropriate command for execution
     * @param command Input/command given by the user.
     * @return The appropriate command to be executed if valid
     * @throws DukeException if the command is invalid
     */
    public static Command parse(String command) throws DukeException {
        String[] output = command.split(" ", 2);
        ValidCommands validCommands = ValidCommands.valueOf(output[0].toUpperCase());
        switch (validCommands) {
            case LIST:
                return new ListCommand();
            case BYE:
                return new ExitCommand();
            case TODO:
                return new AddCommand(new Todo(output[1]));
            case DEADLINE:
                String[] deadlineFormatter = output[1].split(" /by ");
                if (deadlineFormatter.length < 2 ) {
                    throw new DukeException("Either the description or deadline of the task is missing");
                } else {
                    return new AddCommand(new Deadline(deadlineFormatter[0], deadlineFormatter[1]));
                }
            case EVENT:
                String[] details = output[1].split(" /", 3);
                String[] eventFormatter = output[1].split(" /from ");
                System.out.println(eventFormatter[0]);
                if (details.length < 3 ) {
                    throw new IllegalArgumentException("Either the description or dates (from/to) of the task is missing");
                }
                else {
                    String[] startEndDate = eventFormatter[1].split(" /to ");
                    return new AddCommand(new Event(eventFormatter[0], startEndDate[0], startEndDate[1]));
                }
            case MARK:
                return new MarkCommand(Integer.parseInt(output[1]));
            case UNMARK:
                return new UnmarkCommand(Integer.parseInt(output[1]));
            case DELETE:
                return new DeleteCommand(Integer.parseInt(output[1]));
            case FIND:
                return new FindCommand(output[1]);
            case UPDATE:
                return new UpdateCommand(output[1]);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
