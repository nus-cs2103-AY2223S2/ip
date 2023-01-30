package duke;

import duke.command.*;

/**
 * A parser that parses all the user text based commands into the respective commands to be executed.
 */
public class Parser {
    /**
     * Checks on whether the command is recognised. If it is, a corresponding command is returned. Otherwise, an
     * exception is throw to inform them that the command does not exit.
     *
     * @param fullCommand The full string test command that the user input
     * @return The respective command that the user called for
     */
    public static Command parse(String fullCommand) {
        String[] inputSplit = fullCommand.split(" ", 2);
        String command = inputSplit[0];
        switch (command) {
        case "bye":
            return new ExitCommand(command);
        case "list":
            return new ListCommand(command);
        case "mark":
            if (inputSplit.length < 2) {
                throw new DukeException("Mark command missing list numbering.");
            }
            return new MarkCommand(command, inputSplit[1]);
        case "unmark":
            if (inputSplit.length < 2) {
                throw new DukeException("Unmark command missing list numbering.");
            }
            return new UnmarkCommand(command, inputSplit[1]);
        case "todo":
            if (inputSplit.length < 2) {
                throw new DukeException("Todo command missing description.");
            }
            return new AddTodoCommand(command, inputSplit[1]);
        case "deadline":
            if (inputSplit.length < 2) {
                throw new DukeException("Deadline command missing description.");
            }
            return new AddDeadlineCommand(command, inputSplit[1]);
        case "event":
            if (inputSplit.length < 2) {
                throw new DukeException("Event command missing description.");
            }
            return new AddEventCommand(command, inputSplit[1]);
        case "delete":
            if (inputSplit.length < 2) {
                throw new DukeException("Delete command missing list numbering.");
            }
            return new DeleteCommand(command, inputSplit[1]);
        case "find":
            if (inputSplit.length < 2) {
                throw new DukeException("Find command missing terms.");
            }
            return new FindCommand(command, inputSplit[1]);
        default:
            throw new DukeException("Sorry but I don't understand what this means.");
        }
    }
}
