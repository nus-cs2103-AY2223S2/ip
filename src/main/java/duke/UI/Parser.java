package duke.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.*;
import duke.DukeException;
import duke.task.Deadline;

public class Parser {
    protected static final String INPUT_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String LIST = "list";

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
        }
        return command;
    }

    private Command parseTodoCommand(String taskToAdd) {
        String[] index = taskToAdd.split(" ");
        return new TodoCommand(index);
    }

    private Command parseEventCommand(String taskDescription) {
        String[] index = new String[4];
        String[] splitFromDate = taskDescription.split(" /from ");
        String[] splitFromAndToDate = splitFromDate[1].split(" /to ");
        index[2] = splitFromAndToDate[0];
        index[3] = splitFromAndToDate[1];
        String[] nameOfTask = splitFromDate[0].split(" ", 2);
        index[0] = nameOfTask[0];
        index[1] = nameOfTask[1];
        return new EventCommand(index);
    }

    private Command parseDeadlineCommand(String taskDescription) {
        String[] index = new String[3];
        String[] splitDate = taskDescription.split(" /by ");
        String[] splitDeadline = splitDate[0].split(" ", 2);
        index[0] = splitDeadline[0];
        index[1] = splitDeadline[1];
        index[2] = splitDate[1];
        return new DeadlineCommand(index);
    }

    private Command parseInput(String userInput) {
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
            command = new UnmarkCommand(userInput.split(" "));;
        }
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT));
    }
}
