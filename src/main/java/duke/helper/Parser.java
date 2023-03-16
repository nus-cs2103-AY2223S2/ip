package duke.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateTimeException;
import duke.exception.MissingInputsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import javafx.application.Platform;

/**
 * Parser class to parse inputs
 */
public class Parser {
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Parser class
     *
     * @param tasks list of all tasks
     * @param ui class that handles user interaction
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Parses user inputs
     *
     * @param desc description of the task
     * @return output message of command
     * @throws DukeException If input is invalid
     */
    public String parseInputs(String desc) throws DukeException {
        assert desc.length() > 0 : "Inputs should not be empty!";

        String spaceSeparator = " ";
        String[] inputs = desc.trim().split(spaceSeparator, 2);
        String type = inputs[0];

        switch (type) {
        case "list":
            return tasks.outputList();
        case "mark":
            return parseMark(inputs);
        case "unmark":
            return parseUnmark(inputs);
        case "todo":
            return parseToDo(inputs);
        case "deadline":
            return parseDeadline(inputs);
        case "event":
            return parseEvent(inputs);
        case "delete":
            return parseDelete(inputs);
        case "find":
            return parseFind(inputs);
        case "help":
            return parseHelp(inputs);
        case "bye":
            Platform.exit();
            break;
        default:
            throw new InvalidCommandException();
        }
        return "";
    }

    /**
     * Checks if the command has missing inputs
     *
     * @param command command that is being parsed
     * @param inputs inputs by the user
     * @return true if the command does not have missing inputs
     * @throws MissingInputsException If command have missing inputs
     */
    public static boolean checkMissingInputs(String command, String[] inputs) throws MissingInputsException {
        if (inputs.length == 1) {
            throw new MissingInputsException(command);
        }
        return true;
    }

    /**
     * Parses the dateTime description into a LocalDateTime object
     *
     * @param dateTime dateTime description
     * @return a string representation of the LocalDateTime object
     * @throws InvalidDateTimeException If incorrect dateTime values are given
     */
    public static String handleDateTime(String dateTime) throws InvalidDateTimeException {
        assert dateTime.length() > 0 : "DateTime not provided!";
        String dateTimePattern = "MMM-d-yyyy HH:mm";

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
            return LocalDateTime.parse(dateTime).format(formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Parses the mark command
     *
     * @param inputs the different parts of the mark command
     * @return a string to show the display message of the mark command
     * @throws DukeException If no or wrong task number provided
     */
    public String parseMark(String[] inputs) throws DukeException {
        checkMissingInputs("mark", inputs);
        String taskToMark = inputs[1];
        return tasks.changeMarkStatus(true, taskToMark);
    }

    /**
     * Parses the unmark command
     *
     * @param inputs the different parts of the unmark command
     * @return a string to show the display message of the unmark command
     * @throws DukeException If no or wrong task number provided
     */
    public String parseUnmark(String[] inputs) throws DukeException {
        checkMissingInputs("unmark", inputs);
        String taskToUnmark = inputs[1];
        return tasks.changeMarkStatus(false, taskToUnmark);
    }

    /**
     * Parses the todo command
     *
     * @param inputs the different parts of the todo command
     * @return a string to show the creation of a todo task
     * @throws MissingInputsException If the task has no description
     */
    public String parseToDo(String[] inputs) throws MissingInputsException {
        checkMissingInputs("todo", inputs);
        Task toDoTask = new ToDo(inputs[1], false);
        tasks.addToTasks(toDoTask);
        return ui.outputAddTaskMsg(toDoTask, tasks.getTaskList().size());
    }

    /**
     * Parses the deadline command
     *
     * @param inputs different parts of the deadline command
     * @return a string to show the creation of a deadline task
     * @throws MissingInputsException If the command has missing inputs
     * @throws InvalidDateTimeException If the dateTime input is invalid
     */
    public String parseDeadline(String[] inputs) throws MissingInputsException, InvalidDateTimeException {
        String bySeparator = " /by ";

        checkMissingInputs("deadline", inputs);
        String[] deadlineArr = inputs[1].trim().split(bySeparator);
        checkMissingInputs("deadline", deadlineArr);
        String deadlineDesc = deadlineArr[0];
        String by = deadlineArr[1];
        Task deadlineTask = new Deadline(deadlineDesc, handleDateTime(by));
        tasks.addToTasks(deadlineTask);
        return ui.outputAddTaskMsg(deadlineTask, tasks.getSize());
    }

    /**
     * Parses the event command
     *
     * @param inputs different parts of the event command
     * @return a string to show the creation of an event task
     * @throws MissingInputsException If the command has missing inputs
     * @throws InvalidDateTimeException If the dateTime input is invalid
     */
    public String parseEvent(String[] inputs) throws MissingInputsException, InvalidDateTimeException {
        String fromSeparator = " /from ";
        String toSeparator = " /to ";

        checkMissingInputs("event", inputs);
        String[] eventArr = inputs[1].trim().split(fromSeparator);
        checkMissingInputs("event", eventArr);
        String eventDesc = eventArr[0];
        String[] dateTimeArr = eventArr[1].trim().split(toSeparator);
        checkMissingInputs("event", dateTimeArr);
        String from = dateTimeArr[0];
        String to = dateTimeArr[1];

        Task eventTask = new Event(eventDesc,
                handleDateTime(from),
                handleDateTime(to));
        tasks.addToTasks(eventTask);
        return ui.outputAddTaskMsg(eventTask, tasks.getSize());
    }

    /**
     * Parses the delete command
     *
     * @param inputs different parts of the delete command
     * @return a string to show the display message of the delete command
     * @throws DukeException If no or wrong task number provided
     */
    public String parseDelete(String[] inputs) throws DukeException {
        checkMissingInputs("delete", inputs);
        String taskNumber = inputs[1];
        return tasks.deleteTask(taskNumber);
    }

    /**
     * Parses the find command
     *
     * @param inputs different parts of the find command
     * @return a string to show the display message of the find command
     * @throws MissingInputsException If the command has no keyword
     */
    public String parseFind(String[] inputs) throws MissingInputsException {
        checkMissingInputs("find", inputs);
        String keyword = inputs[1];
        ArrayList<Task> taskList = tasks.getTaskList();
        ArrayList<Task> output = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                output.add(task);
            }
        }
        return ui.outputFilterMsg(output);
    }

    /**
     * Parses the help command
     *
     * @param inputs different parts of the help command
     * @return a String showing the user how to use the command
     * @throws MissingInputsException If the help command is not followed by another command
     * @throws InvalidCommandException If the help command is followed by something that is not a command
     */
    public String parseHelp(String[] inputs) throws MissingInputsException, InvalidCommandException {
        checkMissingInputs("help", inputs);
        String command = inputs[1];
        return ui.outputHelpMsg(command);
    }
}
