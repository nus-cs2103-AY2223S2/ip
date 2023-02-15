package duke.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidTaskCommandException;
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
     * Parse user inputs
     *
     * @param desc description of the task
     * @return String representation of Task
     * @throws DukeException If input is invalid
     */
    public String parseInputs(String desc) throws DukeException {
        assert desc.length() > 0 : "Inputs should not be empty!";
        String[] inputs = desc.split(" ", 2);
        String type = inputs[0];

        switch (type) {
            case "list":
                return tasks.outputList();
            case "mark":
                return tasks.markTask(true, inputs[1]);
            case "unmark":
                return tasks.markTask(false, inputs[1]);
            case "todo":
                return parseToDo(inputs);
            case "deadline":
                return parseDeadline(inputs);
            case "event":
                return parseEvent(inputs);
            case "delete":
                return tasks.deleteTask(inputs[1]);
            case "find":
                return parseFind(inputs[1]);
            case "help":
                return parseHelp(inputs[1]);
            case "bye":
                Platform.exit();
            default:
                throw new InvalidTaskCommandException();
        }
    }

    /**
     * Checks if the task desc is not empty
     *
     * @param taskDesc Desc of the task
     * @return true if the task desc is not empty
     * @throws EmptyTaskException If task desc is empty
     */
    public static boolean checkTaskDesc(String[] taskDesc) throws EmptyTaskException {
        if (taskDesc.length == 1) {
            throw new EmptyTaskException(taskDesc[0]);
        }
        return true;
    }

    /**
     * Parse the dateTime description into a LocalDateTime object
     *
     * @param dateTime dateTime description
     * @return a LocalDateTime object
     * @throws InvalidDateTimeException If incorrect dateTime values are given
     */
    public static String handleDateTime(String dateTime) throws InvalidDateTimeException {
        assert dateTime.length() > 0 : "DateTime not provided!";

        try {
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm");
            return LocalDateTime.parse(dateTime).format(pattern);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    public String parseToDo(String[] inputs) throws DukeException {
        checkTaskDesc(inputs);
        Task toDoTask = new ToDo(inputs[1], false);
        tasks.addToTasks(toDoTask);
        return ui.showTaskOutput(toDoTask, tasks.getTasks().size());
    }

    public String parseDeadline(String[] inputs) throws DukeException {
        checkTaskDesc(inputs);
        String[] deadlineDesc = inputs[1].split(" /by ");
        Task deadlineTask = new Deadline(deadlineDesc[0], handleDateTime(deadlineDesc[1]));
        tasks.addToTasks(deadlineTask);
        return ui.showTaskOutput(deadlineTask, tasks.getSize());
    }

    /**
     * Split the event desc
     *
     * @param inputs the desc of an event task
     * @return a string array with all the parts of an event desc
     */
    public String parseEvent(String[] inputs) throws DukeException {
        checkTaskDesc(inputs);
        String eventDesc = inputs[1].split(" /from ")[0];
        String from = inputs[1].split(" /from ")[1].split(" /to ")[0];
        String to = inputs[1].split(" /from ")[1].split(" /to ")[1];

        Task eventTask = new Event(eventDesc,
                handleDateTime(from),
                handleDateTime(to));
        tasks.addToTasks(eventTask);
        return ui.showTaskOutput(eventTask, tasks.getSize());
    }

    public String parseFind(String input) {
        ArrayList<Task> taskList = tasks.getTasks();
        ArrayList<Task> output = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(input)) {
                output.add(task);
            }
        }
        return ui.filter(output);
    }

    public String parseHelp(String input) throws DukeException {
        return ui.showHelpMessage(input);
    }
}
