package duke.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;
import duke.command.*;

public class Parser {
    protected static final String INPUT_DATE_FORMAT = "yyyy-MM-dd HH:mm";

    private Command parseAddCommand(String addCommandType, String taskToAdd) {
        Command command;
        switch (addCommandType) {
        case TodoCommand.COMMAND:
            command = parseTodoCommand(taskToAdd);
            break;
        case EventCommand.COMMAND:
            command = parseEventCommand(taskToAdd);
            break;
        case DeadlineCommand.COMMAND:
            command = parseDeadlineCommand(taskToAdd);
            break;
        default:
            throw new DukeException("I am sorry. I have failed to comprehend your command. Please try again.");
        }
        return command;
    }

    private Command parseTodoCommand(String taskToAdd) {
        String[] index = taskToAdd.split(" ", 2);
        index[1] = index[1].trim();
        if (index.length <= 1 || index[1].isEmpty()) {
            throw new DukeException("The description of a todo task cannot be empty.");
        }
        return new TodoCommand(index);
    }

    private Command parseEventCommand(String taskDescription) {
        String[] index = new String[4];
        String[] splitFromDate = taskDescription.split(" /from ");
        if (splitFromDate.length <= 1) {
            throw new DukeException("Invalid format of an event command.");
        }

        String[] splitFromAndToDate = splitFromDate[1].split(" /to ");
        if (splitFromAndToDate.length <= 1) {
            throw new DukeException("Missing end time of event.");
        }

        index[2] = splitFromAndToDate[0];
        index[3] = splitFromAndToDate[1];
        String[] nameOfTask = splitFromDate[0].split(" ", 2);
        if (nameOfTask.length <= 1) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        index[0] = nameOfTask[0];
        index[1] = nameOfTask[1];
        return new EventCommand(index);
    }

    private Command parseDeadlineCommand(String taskDescription) {
        String[] index = new String[3];
        String[] splitDate = taskDescription.split(" /by ");
        if (splitDate.length <= 1) {
            throw new DukeException("Invalid format of a deadline event.");
        }

        String[] nameOfTask = splitDate[0].split(" ", 2);
        if (nameOfTask.length <= 1) {
            throw new DukeException("The description of a deadline task cannot be empty.");
        }
        index[0] = nameOfTask[0];
        index[1] = nameOfTask[1];
        index[2] = splitDate[1];
        return new DeadlineCommand(index);
    }

    public Command parseInput(String userInput) {
        Command command;
        String inputCommand = userInput.split(" ", 2)[0];
        if (inputCommand.equals(TodoCommand.COMMAND) || inputCommand.equals(EventCommand.COMMAND)
                || inputCommand.equals(DeadlineCommand.COMMAND)) {
            return parseAddCommand(inputCommand, userInput);
        }
        switch (inputCommand) {
        case ByeCommand.COMMAND:
            command = new ByeCommand();
            break;
        case ListCommand.COMMAND:
            command = new ListCommand();
            break;
        case MarkCommand.COMMAND:
            command = new MarkCommand(userInput.split(" "));
            break;
        case UnmarkCommand.COMMAND:
            command = new UnmarkCommand(userInput.split(" "));
            break;
        case DeleteCommand.COMMAND:
            command = new DeleteCommand(userInput.split(" "));
        default:
            throw new DukeException("I am sorry. I have failed to comprehend your command. Please try again.");
        }
        return command;
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT));
    }
}
