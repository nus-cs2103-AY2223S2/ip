package duke.parser;

import duke.commands.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Parser {

    public static Task parseFromFile(String lineFromFile) throws DukeException {
        String[] splitTaskString = lineFromFile.split(" \\| ");
        Task scannedTask;
        boolean markAsDone;
        switch (splitTaskString[0]) {
        case "T":
            scannedTask = new ToDo(splitTaskString[2]);
            markAsDone = splitTaskString[1].equals("1");
            if (markAsDone) {
                scannedTask.markAsDone();
            }
            return scannedTask;
        case "D":
            scannedTask = new Deadline(splitTaskString[2], splitTaskString[3]);
            markAsDone = splitTaskString[1].equals("1");
            if (markAsDone) {
                scannedTask.markAsDone();
            }
            return scannedTask;
        case "E":
            scannedTask = new Event(splitTaskString[2], splitTaskString[3], splitTaskString[4]);
            markAsDone = splitTaskString[1].equals("1");
            if (markAsDone) {
                scannedTask.markAsDone();
            }
            return scannedTask;
        default:
            throw new DukeException("duke.task.Task list file is unreadable/corrupted.");
        }
    }

    public static Command parseFromUser(String fullCommand) throws DukeException {
        String[] splitCommand = fullCommand.split(" ", 2);
        String keyword = splitCommand[0];
        switch (keyword) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            if (isValidIndexCommand(splitCommand)) {
                int index = Integer.parseInt(splitCommand[1]);
                return new MarkCommand(index - 1);
            } else {
                throw new DukeException("Invalid Mark command! Please try again.");
            }
        case "unmark":
            if (isValidIndexCommand(splitCommand)) {
                int index = Integer.parseInt(splitCommand[1]);
                return new UnmarkCommand(index - 1);
            } else {
                throw new DukeException("Invalid Unmark command! Please try again.");
            }
        case "delete":
            if (isValidIndexCommand(splitCommand)) {
                int index = Integer.parseInt(splitCommand[1]);
                return new DeleteCommand(index - 1);
            } else {
                throw new DukeException("Invalid Delete command! Please try again.");
            }
        case "todo":
            if (isValidAddCommand(splitCommand)) {
                return new AddToDoCommand(splitCommand[1]);
            } else {
                throw new DukeException("Description of Todo task cannot be empty");
            }
        case "deadline":
            String[] splitDescription = splitCommand[1].split(" /by ");
            if (isValidAddCommand(splitCommand) && splitDescription.length == 2) {
                return new AddDeadlineCommand(splitDescription[0], splitDescription[1]);
            } else {
                throw new DukeException("Description and date of Deadline task cannot be empty and " +
                        "must be separated with \"/by\" . ");
            }
        case "event":
            if (isValidAddCommand(splitCommand)) {
                String[] furtherSplitCommand = splitCommand[1].split(" /from ");//checked index 1 exists
                String description = furtherSplitCommand[0];
                try {
                    String[] fromToArray = furtherSplitCommand[1].split(" /to ");
                    return new AddEventCommand(description, fromToArray[0], fromToArray[1]);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Event task must have /from and /to");
                }
            } else {
                throw new DukeException("Description and from/to of Event task cannot be empty.");
            }
        case "find":
            if (isValidFindCommand(splitCommand)) {
                String findKeyword = splitCommand[1];
                return new FindCommand(findKeyword);
            } else {
                throw new DukeException("Keyword of find command cannot be empty.");
            }
        default:
            //TODO add help in the future to show all duke.commands to user
            throw new DukeException("Unrecognised command. Please try again.");
        }
    }

    /** Determines if command consists of correct keyword and is followed by some description. */
    private static boolean isValidAddCommand(String[] splitCommand) {
        String keyword = splitCommand[0];
        switch (keyword) {
        case "todo":
        case "deadline":
        case "event":
            return !splitCommand[1].isBlank();
        default:
            return false;
        }
    }

    private static boolean isValidIndexCommand(String[] splitCommand) {
        if (splitCommand.length >= 2) {
            try {
                int num = Integer.parseInt(splitCommand[1]);
                return num == 0 || num == 1;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Determine if command is a valid find command
     * @param splitCommand arr of String
     * @return true if array has 2 elements and second element is not blank (empty or whitespace)
     */
    private static boolean isValidFindCommand(String[] splitCommand) {
        return splitCommand.length == 2 && !splitCommand[1].isBlank();
    }

}
