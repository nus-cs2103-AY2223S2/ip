package duke.parser;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.CommandType;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.DisplayListCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.MarkTaskAsDoneCommand;
import duke.commands.MarkTaskAsUndoneCommand;
import duke.exception.CannotReadFileDukeException;
import duke.exception.DukeException;
import duke.exception.EmptyArgumentDukeException;
import duke.exception.InvalidArgumentDukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public LocalDateTime parseDateTime(String str) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(str, formatter);
    }

    public Task parseSave(String line) throws CannotReadFileDukeException {
        String[] parsed = line.split("\\|");
        String taskSymbol = parsed[0];
        boolean isTaskDone = Boolean.parseBoolean(parsed[1]);
        String description = parsed[2];
        Task task;
        switch (taskSymbol) {
        case "T":
            task = new ToDoTask(description);
            break;
        case "D":
            task = new DeadlineTask(description, parseDateTime(parsed[3]));
            break;
        case "E":
            task = new EventTask(description, parseDateTime(parsed[3]), parseDateTime(parsed[4]));
            break;
        default:
            throw new CannotReadFileDukeException();
        }
        if (isTaskDone) {
            task.setDone(true);
        }
        return task;
    }

    public Command parseCommand(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        CommandType commandType = CommandType.getCommandType(command);
        // duke.commands with no arguments
        switch (commandType) {
        case EXIT:
            return new ExitCommand();
        case DISPLAY_LIST:
            return new DisplayListCommand();
        }

        // duke.commands with arguments
        if (parts.length < 2) {
            throw new EmptyArgumentDukeException();
        }
        switch (commandType) {
        case MARK_TASK_AS_DONE:
            try {
                int number = Integer.parseInt(parts[1]);
                return new MarkTaskAsDoneCommand(number);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentDukeException();
            }
        case MARK_TASK_AS_UNDONE:
            try {
                int number = Integer.parseInt(parts[1]);
                return new MarkTaskAsUndoneCommand(number);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new InvalidArgumentDukeException();
            }
        case DELETE:
            try {
                int number = Integer.parseInt(parts[1]);
                return new DeleteTaskCommand(number);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentDukeException();
            }
        case TODO:
            return new AddTodoCommand(parts[1]);
        case DEADLINE:
            try {
                String[] splitArgs = parts[1].split(" /by ");
                return new AddDeadlineCommand(splitArgs[0], parseDateTime(splitArgs[1]));
            } catch (DateTimeParseException e) {
                throw new InvalidArgumentDukeException();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EmptyArgumentDukeException();
            }
        case EVENT:
            try {
                String[] splitArgs = parts[1].split(" /from ");
                String[] times = splitArgs[1].split(" /to ");
                return new AddEventCommand(splitArgs[0], parseDateTime(times[0]), parseDateTime(times[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EmptyArgumentDukeException();
            } catch (DateTimeParseException e) {
                throw new InvalidArgumentDukeException();
            }
        case FIND:
            try {
                String keyword = parts[1];
                return new FindCommand(keyword);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EmptyArgumentDukeException();
            }
        }

        return null;
    }
}
