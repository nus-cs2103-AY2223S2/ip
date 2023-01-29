package duke;

import duke.command.*;

public class Parser {
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
