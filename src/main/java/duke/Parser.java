package duke;

import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

import duke.exception.DukeException;
import duke.exception.IndexErrorType;

import duke.gui.GuiText;

import duke.task.Task;
import duke.task.TaskType;

/** Class that parses user input */
public class Parser {

    /** Constant Strings of the delimiters used in commands */
    private static final String BY_INDICATOR = "/by";
    private static final String FROM_INDICATOR = "/from";
    private static final String TO_INDICATOR = "/to";

    /**
     * Parses the given command string.
     *
     * @param line String representing user command.
     * @return Command object representing user command.
     * @throws DukeException if given command string cannot be parsed.
     */
    public static Command parseCommand(String line) throws DukeException {
        if (line.toUpperCase().equals(CommandType.BYE.name())) {
            return new ExitCommand();
        } else if (line.toUpperCase().equals(CommandType.LIST.name())) {
            return new ListCommand();
        } else if (line.toUpperCase().equals(CommandType.HELP.name())) {
            return new HelpCommand();
        } else {
            return processCommand(line);
        }
    }

    /**
     * Parses multi-word command strings.
     *
     * @param line String representing multi-word command.
     * @return Command object representing multi-word command.
     * @throws DukeException if given multi-word command string cannot be parsed.
     */
    private static Command processCommand(String line) throws DukeException {
        String[] splitCommand = line.split(" ");
        String command = splitCommand[0];
        String uppercaseCommand = command.toUpperCase();
        if (uppercaseCommand.equals(CommandType.MARK.name())
                || uppercaseCommand.equals(CommandType.UNMARK.name())
                || uppercaseCommand.equals(CommandType.DELETE.name())) {
            return processTask(uppercaseCommand, splitCommand);
        } else if (uppercaseCommand.equals(CommandType.TODO.name())
                || uppercaseCommand.equals(CommandType.DEADLINE.name())
                || uppercaseCommand.equals(CommandType.EVENT.name())) {
            return addTask(command, TaskType.matchStringToTaskType(uppercaseCommand), line);
        } else if (uppercaseCommand.equals(CommandType.FIND.name())) {
            return processFind(command, line);
        } else {
            throw new DukeException(GuiText.generateUnknownCommandErrorMessage());
        }
    }

    /**
     * Parses command strings to do with altering the task list
     * or the state of tasks.
     *
     * @param uppercaseCommand Command keyword in uppercase.
     * @param splitCommand Command string split by spaces.
     * @return Command object representing user command.
     * @throws DukeException if given command string cannot be parsed.
     */
    private static Command processTask(String uppercaseCommand, String[] splitCommand) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException(GuiText.generateIndexErrorMessage(IndexErrorType.NO_NUMBER));
        } else if (splitCommand.length > 2) {
            throw new DukeException(GuiText.generateIndexErrorMessage(IndexErrorType.TOO_MANY_NUMBERS));
        }

        try {
            int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            if (uppercaseCommand.equals(CommandType.MARK.name())) {
                return new MarkCommand(taskIndex);
            } else if (uppercaseCommand.equals(CommandType.UNMARK.name())) {
                return new UnmarkCommand(taskIndex);
            } else if (uppercaseCommand.equals(CommandType.DELETE.name())) {
                return new DeleteCommand(taskIndex);
            } else {
                throw new DukeException(GuiText.generateGenericErrorMessage());
            }
        } catch (NumberFormatException e) {
            throw new DukeException(GuiText.generateIndexErrorMessage(IndexErrorType.NOT_A_NUMBER));
        }
    }

    /**
     * Parses command strings to do with adding tasks.
     *
     * @param command Command keyword.
     * @param taskType The type of task being created.
     * @param line String representing user command.
     * @return Command object.
     * @throws DukeException if task cannot be created.
     */
    private static Command addTask(String command, TaskType taskType, String line) throws DukeException {
        Task task = null;
        try {
            switch (taskType) {
            case TODO:
                task = addTodo(command, line);
                break;
            case DEADLINE:
                task = addDeadline(command, line);
                break;
            case EVENT:
                task = addEvent(command, line);
                break;
            default:
                throw new DukeException(GuiText.generateGenericErrorMessage());
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(GuiText.generateMissingArgumentErrorMessage(taskType));
        } catch (DateTimeParseException e) {
            throw new DukeException(GuiText.generateDateErrorMessage());
        }
        assert task != null : "Task should not be null";
        return new AddCommand(task);
    }

    /**
     * Parses command strings to do with adding todos.
     *
     * @param command Command keyword using in string.
     * @param line String representing user command.
     * @return Task representing the todo.
     * @throws DukeException if any arguments are invalid.
     */
    private static Task addTodo(String command, String line) throws DukeException {
        String description = line.split(command)[1].trim();

        if (description.isEmpty()) {
            throw new DukeException(GuiText.generateMissingArgumentErrorMessage(TaskType.TODO));
        }

        return Task.createTask(TaskType.TODO, description);
    }

    /**
     * Parses command strings to do with adding deadlines.
     *
     * @param command Command keyword using in string.
     * @param line String representing user command.
     * @return Task representing the deadline.
     * @throws DukeException if any arguments are invalid.
     */
    private static Task addDeadline(String command, String line) throws DukeException {
        String details = line.split(command)[1].trim();

        String[] detailsSplitByBy = details.split(Parser.BY_INDICATOR);
        
        String name = detailsSplitByBy[0].trim();
        String deadline = detailsSplitByBy[1].trim();

        if (name.isEmpty() || deadline.isEmpty()) {
            throw new DukeException(GuiText.generateMissingArgumentErrorMessage(TaskType.DEADLINE));
        }

        return Task.createTask(TaskType.DEADLINE, name, deadline);
    }

    /**
     * Parses command strings to do with adding events.
     *
     * @param command Command keyword using in string.
     * @param line String representing user command.
     * @return Task representing the event.
     * @throws DukeException if any arguments are invalid.
     */
    private static Task addEvent(String command, String line) throws DukeException {
        String details = line.split(command)[1].trim();

        String[] detailsSplitByFrom = details.split(Parser.FROM_INDICATOR);

        String name = detailsSplitByFrom[0].trim();

        String[] splitByTo = detailsSplitByFrom[1].split(Parser.TO_INDICATOR);

        String startDate = splitByTo[0].trim();
        String endDate = splitByTo[1].trim();

        if (name.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            throw new DukeException(GuiText.generateMissingArgumentErrorMessage(TaskType.EVENT));
        }

        return Task.createTask(TaskType.EVENT, name, startDate, endDate);
    }

    /**
     * Parses command strings to do with finding matching
     * tasks.
     *
     * @param command Command keyword using in string.
     * @param line String representing user command.
     * @return Command object.
     * @throws DukeException if no keyword is provided.
     */
    private static Command processFind(String command, String line) throws DukeException {
        try {
            String[] keywords = line.split(command)[1].trim().split(" ");
            return new FindCommand(keywords);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(GuiText.generateMissingKeywordErrorMessage());
        }
    }

}
