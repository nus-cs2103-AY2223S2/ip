package angela.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import angela.commands.AddDeadlineCommand;
import angela.commands.AddEventCommand;
import angela.commands.AddTodoCommand;
import angela.commands.Command;
import angela.commands.CommandType;
import angela.commands.DeleteTaskCommand;
import angela.commands.DisplayListCommand;
import angela.commands.ExitCommand;
import angela.commands.FindCommand;
import angela.commands.MarkTaskAsDoneCommand;
import angela.commands.MarkTaskAsUndoneCommand;
import angela.commands.ReminderCommand;
import angela.exception.AngelaException;
import angela.exception.CannotReadFileAngelaException;
import angela.exception.EmptyArgumentAngelaException;
import angela.exception.InvalidArgumentAngelaException;
import angela.task.DeadlineTask;
import angela.task.EventTask;
import angela.task.Task;
import angela.task.ToDoTask;

/**
 * A class that deals with making sense of the user command and saved content.
 */
public class Parser {

    /**
     * Converts a string to a LocalDateTime.
     *
     * @param str The given string.
     * @return The time converted from the given string.
     * @throws DateTimeParseException If the string is not in the correct format.
     */
    public LocalDateTime parseDateTime(String str) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(str, formatter);
    }

    /**
     * Converts a line of string to a task.
     *
     * @param line A line of string in a save format.
     * @return A task converted from the given string.
     * @throws CannotReadFileAngelaException If the string is not formatted correctly.
     */
    public Task parseSave(String line) throws CannotReadFileAngelaException {
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
            String deadlineEndTime = parsed[3];
            task = new DeadlineTask(description, parseDateTime(deadlineEndTime));
            break;
        case "E":
            String eventStartTime = parsed[3];
            String eventEndTime = parsed[4];
            task = new EventTask(description, parseDateTime(eventStartTime), parseDateTime(eventEndTime));
            break;
        default:
            throw new CannotReadFileAngelaException();
        }

        setTaskDoneValue(task, isTaskDone);
        return task;
    }

    private void setTaskDoneValue(Task task, boolean isDone) {
        task.setDone(isDone);
    }

    /**
     * Converts a string to a command.
     *
     * @param input A string.
     * @return A command converted from the given string.
     * @throws AngelaException If the string is not formatted correctly.
     */
    public Command parseCommand(String input) throws AngelaException {
        String[] parts = input.split(" ", 2);
        String commandString = parts[0];
        CommandType commandType = CommandType.getCommandType(commandString);

        Command command = parseCommandWithNoArgument(commandType);
        if (command != null) {
            return command;
        }

        return parseCommandWithArgument(commandType, parts);
    }

    private Command parseCommandWithNoArgument(CommandType commandType) {
        switch (commandType) {
        case EXIT:
            return new ExitCommand();
        case DISPLAY_LIST:
            return new DisplayListCommand();
        case REMINDERS:
            return new ReminderCommand();
        default:
            return null;
        }
    }

    private Command parseCommandWithArgument(CommandType commandType, String[] parts) throws AngelaException {
        if (parts.length < 2) {
            throw new EmptyArgumentAngelaException();
        }

        String arguments = parts[1];

        switch (commandType) {
        case MARK_TASK_AS_DONE:
            return parseMarkDoneCommand(arguments);
        case MARK_TASK_AS_UNDONE:
            return parseMarkUndoneCommand(arguments);
        case DELETE:
            return parseDeleteCommand(arguments);
        case TODO:
            return parseTodoCommand(arguments);
        case DEADLINE:
            return parseDeadlineCommand(arguments);
        case EVENT:
            return parseEventCommand(arguments);
        case FIND:
            return parseFindCommand(arguments);
        default:
            assert false : "Should not reach this point";
        }

        return null;
    }

    private Command parseMarkDoneCommand(String arguments) throws InvalidArgumentAngelaException {
        try {
            int number = Integer.parseInt(arguments);
            return new MarkTaskAsDoneCommand(number);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentAngelaException();
        }
    }

    private Command parseMarkUndoneCommand(String arguments) throws InvalidArgumentAngelaException {
        try {
            int number = Integer.parseInt(arguments);
            return new MarkTaskAsUndoneCommand(number);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentAngelaException();
        }
    }

    private Command parseDeleteCommand(String arguments) throws InvalidArgumentAngelaException {
        try {
            int number = Integer.parseInt(arguments);
            return new DeleteTaskCommand(number);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentAngelaException();
        }
    }

    private Command parseTodoCommand(String arguments) {
        return new AddTodoCommand(arguments);
    }

    private Command parseDeadlineCommand(String arguments) throws AngelaException {
        try {
            String[] splitArgs = arguments.split(" /by ");
            return new AddDeadlineCommand(splitArgs[0], parseDateTime(splitArgs[1]));
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentAngelaException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyArgumentAngelaException();
        }
    }

    private Command parseEventCommand(String arguments) throws AngelaException {
        try {
            String[] splitArgs = arguments.split(" /from ");
            String[] times = splitArgs[1].split(" /to ");
            return new AddEventCommand(splitArgs[0], parseDateTime(times[0]), parseDateTime(times[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyArgumentAngelaException();
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentAngelaException();
        }
    }

    private Command parseFindCommand(String arguments) throws EmptyArgumentAngelaException {
        try {
            return new FindCommand(arguments);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyArgumentAngelaException();
        }
    }
}
