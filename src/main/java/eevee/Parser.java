package eevee;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import eevee.command.Command;
import eevee.command.MarkCommand;
import eevee.command.AddToDoCommand;
import eevee.command.FindCommand;
import eevee.command.AddDeadlineCommand;
import eevee.command.AddEventCommand;
import eevee.command.DeleteCommand;
import eevee.command.UnmarkCommand;
import eevee.command.ExitCommand;
import eevee.command.ListCommand;
import eevee.command.HelpCommand;
import eevee.exception.EeveeException;
import eevee.exception.NoTaskToDeleteException;
import eevee.exception.TaskNoContentException;
import eevee.exception.TaskNoNameException;
import eevee.task.Task;
import eevee.task.ToDo;
import eevee.task.Deadline;
import eevee.task.Event;

public class Parser {

    private final static int TASK_TYPE_INDEX = 0;
    private final static int TASK_IS_DONE_INDEX = 1;
    private final static int TASK_DESCRIPTION = 2;
    private final static int START_TIME_INDEX = 3;
    private final static int END_TIME_INDEX = 4;
    private final static int TODO_TASK_DESCRIPTION_INDEX = 5;
    private final static int DEADLINE_TASK_DESCRIPTION_INDEX = 9;
    private final static int EVENT_TASK_DESCRIPTION_INDEX = 6;
    private final static int TASK_NAME_INDEX = 0;
    private final static int TASK_START_TIME_INDEX = 1;
    private final static int TASK_END_TIME_INDEX = 2;
    private final static int TO_REMOVE_LEADING_FOUR_LETTERS = 5;
    private final static int TO_REMOVE_LEADING_TWO_LETTERS = 3;

    /**
     * Converts a task from the task list format
     * to a Task object
     * @param line line to be converted to a <code>Task</code>
     * @return a <code>Task</code>> object with the relevant information
     */
    public static Task convertTaskFromLineInTaskList(String line) {
        String[] taskInfo = line.split("[|]");
        String taskType = taskInfo[TASK_TYPE_INDEX].trim();
        boolean isDone = taskInfo[TASK_IS_DONE_INDEX].trim().equals("1");
        String taskDescription = taskInfo[TASK_DESCRIPTION].trim();
        switch (taskType) {
        case "T":
            return new ToDo(taskDescription, isDone);
        case "D":
            String taskTime = taskInfo[START_TIME_INDEX].trim();
            return new Deadline(taskDescription, taskTime, isDone);
        case "E":
            String startTime = taskInfo[START_TIME_INDEX].trim();
            String endTime = taskInfo[END_TIME_INDEX].trim();
            return new Event(taskDescription, startTime, endTime, isDone);
        default:
            throw new IllegalStateException("Unexpected value: " + taskType);
        }
    }

    /**
     * Makes a <code>ToDos</code> object from the given line.
     * @param line information of the <code>ToDos</code> in a
     *             <code>String</code> format
     * @return a <code>Task</code> object of the <code>ToDos</code>
     */
    public static Task makeTodoFromCommand(String line) throws TaskNoNameException {
        try {
            String taskName = line.substring(TODO_TASK_DESCRIPTION_INDEX);
            return new ToDo(taskName);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNoNameException();
        }
    }

    /**
     * Makes a <code>Deadlines</code> object from the given line.
     * @param line information of the <code>Deadlines</code> in a
     *             <code>String</code> format
     * @return a <code>Task</code> object of the <code>Deadlines</code>
     */
    public static Task makeDeadlineFromCommand(String line) throws TaskNoContentException, TaskNoNameException {
        try {
            String taskInfo = line.substring(DEADLINE_TASK_DESCRIPTION_INDEX);
            String taskName = taskInfo.split("/by")[TASK_NAME_INDEX].strip();
            if (taskName.isBlank()) {
                throw new TaskNoNameException();
            }
            String taskDeadline = taskInfo.split("/by ")[TASK_START_TIME_INDEX];
            return new Deadline(taskName, taskDeadline);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNoContentException();
        }
    }

    /**
     * Makes a <code>Events</code> object from the given line.
     * @param line information of the <code>Events</code> in a
     *             <code>String</code> format
     * @return a <code>Task</code> object of the <code>Events</code>
     */
    public static Task makeEventFromCommand(String line) throws TaskNoContentException, TaskNoNameException {
        try {
            String taskInfo = line.substring(EVENT_TASK_DESCRIPTION_INDEX);
            String taskName = taskInfo.split("/")[TASK_NAME_INDEX].strip();
            if (taskName.isBlank()) {
                throw new TaskNoNameException();
            }
            String[] taskInfoTimes = taskInfo.split("/");
            String taskStart = taskInfoTimes[TASK_START_TIME_INDEX].substring(TO_REMOVE_LEADING_FOUR_LETTERS).strip();
            String taskEnd = taskInfoTimes[TASK_END_TIME_INDEX].substring(TO_REMOVE_LEADING_TWO_LETTERS).strip();
            return new Event(taskName, taskStart, taskEnd);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNoContentException();
        }

    }

    /**
     * Gets the search word from the given line of command.
     * @param line line of command containing the search word
     * @return the search word in a String
     */
    public static String getSearchWord(String line) {
        return line.substring(TO_REMOVE_LEADING_FOUR_LETTERS);
    }

    /**
     * Gets the index needed from the given command.
     * @param command the user command
     * @return the index in the form of an <code>int</code>
     */
    public static int getIndexFromCommand(String command) {
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }

    /**
     * Gets the type of command from the given command.
     * @param command the user command
     * @return the type of command in the form of a String
     */
    public static String getTypeOfCommand(String command) {
        return command.split(" ")[0];
    }

    /**
     * Converts a String of command to a Command object
     * @param command the user command
     * @return a Command object
     * @throws EeveeException if command is not recognised
     */
    public static Command getCommand(String command) throws EeveeException {
        String typeOfCommand = getTypeOfCommand(command);
        switch (typeOfCommand) {
        case "bye":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(command);
        case "unmark":
            return new UnmarkCommand(command);
        case "delete":
            return new DeleteCommand(command);
        case "todo":
            return new AddToDoCommand(command);
        case "deadline":
            return new AddDeadlineCommand(command);
        case "event":
            return new AddEventCommand(command);
        case "find":
            return new FindCommand(command);
        default:
            throw new EeveeException("Command not recognised.");
        }
    }

    /**
     * Handles the Command by executing it
     * @param command the Command to handle
     * @param ui the Ui for output message
     * @param tasks the TaskList containing the current tasks
     * @param storage the Storage object for handling saving to hard drive
     * @return a String to output to user
     * @throws EeveeException if command is not recognised
     * @throws IOException if something goes wrong when saving to hard drive
     * @throws IndexOutOfBoundsException if the command is not in the correct format so command cannot be handled
     * @throws DateTimeParseException if format of date and time given is wrong
     * @throws TaskNoContentException if no content is given for the task
     * @throws NoTaskToDeleteException if the index given to delete is out of bounds
     * @throws TaskNoNameException if no name is given for the task
     */
    public static String handleCommand(Command command, Ui ui, TaskList tasks, Storage storage) throws EeveeException,
            IOException, IndexOutOfBoundsException, DateTimeParseException, TaskNoContentException,
            NoTaskToDeleteException, TaskNoNameException {
        return command.execute(tasks, ui, storage);
    }
}
