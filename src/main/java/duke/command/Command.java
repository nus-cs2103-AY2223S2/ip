package duke.command;

import duke.Duke;
import duke.DukeException;

public abstract class Command {

    public enum ReturnCode {
        SUCCESS,
        FAILURE, EXIT, FATAL
    }

    public abstract ReturnCode execute(Duke duke) throws DukeException;

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
