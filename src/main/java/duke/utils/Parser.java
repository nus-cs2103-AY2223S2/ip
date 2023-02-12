package duke.utils;

import java.io.File;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UndoCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.EmptyCommandException;
import duke.exceptions.InvalidCommandValueException;
import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidTaskTypeException;
import duke.exceptions.InvalidTimeException;
import duke.exceptions.InvalidUndoException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.TaskTypes;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * Parser to convert user inputs to executable commands.
 */
public class Parser {

    /**
     * Parses a command from a string.
     *
     * @param userCommands The string command to parse.
     * @param commandList The task list that has list of tasks.
     * @param storage The storage that store tasks from previous and new sessions.
     * @param ui The ui used for the bot.
     * @param file The file that data is read and written from.
     * @return The command corresponding to the user input.
     * @throws InvalidCommandValueException If a delete, mark or unmark command specify a wrong index.
     * @throws InvalidTaskTypeException If no such task type exists.
     * @throws EmptyCommandException If the existing command is empty.
     * @throws InvalidTimeException If the format of event or deadline is unrecognised.
     * @throws InvalidDateException If the date entered for event or deadline is unrecognised.
     */

    public Command parse(String userCommands, TaskList commandList,
                         Storage storage, Ui ui, File file)
            throws InvalidCommandValueException, InvalidTaskTypeException,
            EmptyCommandException, InvalidTimeException, InvalidDateException,
            InvalidUndoException {
        String[] strArray = userCommands.split(" ", 2);
        String action = strArray[0];

        if (action.equalsIgnoreCase("bye")) {
            return new ByeCommand(ui);
        } else if (action.equalsIgnoreCase("list")) {
            return new ListCommand(ui, commandList);
        } else if (action.equalsIgnoreCase("mark")) {
            if (strArray.length < 2 || strArray[1].trim().equals("")) {
                throw new EmptyCommandException(action);
            }
            return new MarkCommand(ui, commandList,
                    Integer.parseInt(strArray[1]) - 1, storage, file);
        } else if (action.equalsIgnoreCase("unmark")) {
            if (strArray.length < 2 || strArray[1].trim().equals("")) {
                throw new EmptyCommandException(action);
            }
            return new UnmarkCommand(ui, commandList,
                    Integer.parseInt(strArray[1]) - 1, storage, file);
        } else if (action.equalsIgnoreCase("delete")) {
            if (strArray.length < 2 || strArray[1].trim().equals("")) {
                throw new EmptyCommandException(action);
            }
            return new DeleteCommand(ui, commandList,
                    Integer.parseInt(strArray[1]) - 1, storage, file);
        } else if (action.equalsIgnoreCase("find")) {
            if (strArray.length < 2 || strArray[1].trim().equals("")) {
                throw new EmptyCommandException(action);
            }
            return new FindCommand(ui, commandList, strArray[1]);
        } else if (action.equalsIgnoreCase("undo")) {
            if (Command.isEmpty()) {
                throw new InvalidUndoException();
            }
            return new UndoCommand(ui);
        } else {
            TaskTypes type = getTaskType(action);
            Task task = getTask(type, strArray);
            return new AddCommand(ui, commandList, task, storage, file);
        }
    }

    /**
     * Retrieve a task type for a given action.
     *
     * @param action The string command to parse.
     * @return The task type corresponding to the action.
     * @throws InvalidTaskTypeException If no such task type exists.
     */
    public TaskTypes getTaskType(String action)
            throws InvalidTaskTypeException {
        if (action.equalsIgnoreCase("todo")) {
            return TaskTypes.TODO;
        } else if (action.equalsIgnoreCase("deadline")) {
            return TaskTypes.DEADLINE;
        } else if (action.equalsIgnoreCase("event")) {
            return TaskTypes.EVENT;
        } else {
            throw new InvalidTaskTypeException();
        }
    }

    /**
     * Return a task for given its type and string array input.
     *
     * @param type The task type to convert.
     * @param strArray The string array of the command
     * @return The task corresponding to its type and string array.
     * @throws InvalidTaskTypeException If no such task type exists.
     * @throws EmptyCommandException If the existing command is empty
     * @throws InvalidTimeException If the format of event or deadline is unrecognised.
     * @throws InvalidDateException If the date entered for event or deadline is unrecognised.
     */
    public Task getTask(TaskTypes type, String[] strArray)
            throws InvalidTaskTypeException, EmptyCommandException,
            InvalidTimeException, InvalidDateException {
        String command;

        if (strArray.length < 2 || strArray[1].trim().equals("")) {
            throw new EmptyCommandException(strArray[0]);
        }

        if (type.equals(TaskTypes.TODO)) {
            command = strArray[1].trim();
            return new ToDo(command);
        } else if (type.equals(TaskTypes.DEADLINE)) {
            String[] temp = strArray[1].split("/by", 2);
            if (temp.length < 2 || temp[1].trim().equals("")) {
                throw new InvalidTimeException();
            }
            command = temp[0].trim();
            String deadline = temp[1].trim();
            return new Deadline(command, deadline);
        } else if (type.equals(TaskTypes.EVENT)) {
            String[] temp = strArray[1].split("/from", 2);
            if (temp.length < 2 || temp[1].trim().equals("")) {
                throw new InvalidTimeException();
            }
            String[] temp2 = temp[1].split("/to", 2);
            if (temp2.length < 2 || temp2[1].trim().equals("")) {
                throw new InvalidTimeException();
            }
            command = temp[0].trim();
            String start = temp2[0].trim();
            String end = temp2[1].trim();
            return new Event(command, start, end);
        } else {
            throw new InvalidTaskTypeException();
        }
    }
}
