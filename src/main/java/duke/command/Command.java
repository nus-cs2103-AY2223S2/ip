package duke.command;

import duke.Duke;
import duke.DukeException;

/**
 * Serves as the parent class for all Commands.
 * Also serves as a parser for Commands.
 */
public abstract class Command {

    /**
     * Enum of the return status of a command.
     * SUCCESS = Command has run successfully with nothing wrong.
     * FAILURE = Command has run, but may have occurred some user problems along the way.
     * FATAL = Command has failed to run, something bad had happened.
     */
    public enum ReturnCode {
        SUCCESS, FAILURE, EXIT, FATAL
    }

    /**
     * Does what the specific command is supposed to do.
     * Implementation vary according to command.
     *
     * @param duke A reference to the duke called the execute(...).
     * @return The ReturnCode of the command.
     * @throws DukeException Depending on implementation.
     */
    public abstract ReturnCode execute(Duke duke) throws DukeException;

    /**
     * Parses a String (provided by user) into the correct Command.
     *
     * @param userInput String containing the whole input provided by the user.
     * @return A new instance of the correct Command.
     *         A null of parser is unable to match userInput to the correct Command.
     * @throws DukeException Depending on implementation of the command.
     */
    public static Command parseCommand(String userInput) throws DukeException {
        String cmd = userInput.split(" ", 2)[0];

        switch(cmd) {
        case "ls":
        case "list":
            return new ListCommand();

        case "todo":
            return new AddTodoCommand(userInput);

        case "deadline":
            return new AddDeadlineCommand(userInput);

        case "event":
            return new AddEventCommand(userInput);

        case "mark":
            return new MarkCommand(userInput);

        case "unmark":
            return new UnmarkCommand(userInput);

        case "delete":
            return new DeleteCommand(userInput);

        case "find":
            return new FindCommand(userInput);

        case "save":
            return new SaveCommand();

        case "bye":
        case "goodbye":
        case ":q":
        case "quit":
        case "quit()":
        case "exit":
        case "exit()":
            return new ExitCommand();

        default:
            return null; // Parse failed: Command not found
        }
    }

}
