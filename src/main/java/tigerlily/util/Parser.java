package tigerlily.util;

import tigerlily.commands.ByeCommand;
import tigerlily.commands.Command;
import tigerlily.commands.DeadlineCommand;
import tigerlily.commands.DeleteCommand;
import tigerlily.commands.EventCommand;
import tigerlily.commands.FindCommand;
import tigerlily.commands.HelpCommand;
import tigerlily.commands.ListCommand;
import tigerlily.commands.MarkCommand;
import tigerlily.commands.ToDoCommand;
import tigerlily.commands.UnmarkCommand;

import tigerlily.exceptions.DukeExceptions;
import tigerlily.exceptions.InvalidFilePathException;
import tigerlily.exceptions.UnknownCommandException;

import tigerlily.tasks.Deadline;
import tigerlily.tasks.Event;
import tigerlily.tasks.Task;
import tigerlily.tasks.ToDo;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    private boolean isBye = false;
    public enum CommandType {BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND, HELP}

    /**
     * Handles the commands given by a user and returns the appropriate command to be executed.
     *
     * @param input the input given by a user
     * @return the required Command
     * @throws UnknownCommandException If a given command doesn't match the supported command types
     */
    public Command handleCommand(String input) throws DukeExceptions {
        try {
            CommandType command = CommandType.valueOf(input.split(" ")[0].toUpperCase());
            switch (command) {
            case BYE:
                isBye = true;
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case TODO:
                return new ToDoCommand(input);
            case DEADLINE:
                return new DeadlineCommand(input);
            case EVENT:
                return new EventCommand(input);
            case MARK:
                return new MarkCommand(input);
            case UNMARK:
                return new UnmarkCommand(input);
            case DELETE:
                return new DeleteCommand(input);
            case FIND:
                return new FindCommand(input);
            case HELP:
                return new HelpCommand(input);
            default:
                throw new UnknownCommandException();
            }
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
    }

    /**
     * Translates the Tasks saved in storage into TaskList format as needed and creates the corresponding Task.
     *
     * @param input the String of the Task saved in storage
     * @return the resulting Task
     * @throws InvalidFilePathException If the file path of storage doesn't exist
     */
    public Task translateFileToTaskList(String input) throws DukeExceptions {
        String[] splitInput = input.split(" \\| ");
        String taskType = splitInput[0];
        String taskStatus = splitInput[1];
        String taskDescription = splitInput[2];
        Task newTask;

        switch (taskType) {
        case "T":
            newTask = new ToDo(taskDescription);
            break;
        case "E":
            String[] eventTiming = splitInput[3].split("to");
            newTask = new Event(taskDescription, LocalDateTime.parse(eventTiming[0].trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    LocalDateTime.parse(eventTiming[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            break;
        case "D":
            newTask = new Deadline(taskDescription, LocalDate.parse(splitInput[3],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            break;
        default:
            throw new InvalidFilePathException();
        }
        if (taskStatus.equals("1")) {
            newTask.markDone();
        } else if (taskStatus.equals("0")) {
            newTask.unmarkDone();
        }
        return newTask;
    }

    public boolean isBye() {
        return this.isBye;
    }
}