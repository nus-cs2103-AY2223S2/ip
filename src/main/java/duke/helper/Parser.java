package duke.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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
        String[] inputs = desc.split(" ", 2);
        String type = inputs[0];

        switch (type) {
        case "list":
            return tasks.outputList();

        case "mark":
            return tasks.mark(true, inputs[1]);

        case "unmark":
            return tasks.mark(false, inputs[1]);

        case "todo":
            checkTaskDesc(inputs);
            Task toDoTask = new ToDo(inputs[1], false);
            tasks.addToTasks(toDoTask);
            return ui.showTaskOutput(toDoTask, tasks.getTasks().size());

        case "deadline":
            checkTaskDesc(inputs);
            String[] deadlineDesc = inputs[1].split(" /by ");

            Task deadlineTask = new Deadline(deadlineDesc[0], handleDateTime(deadlineDesc[1]).toString());
            tasks.addToTasks(deadlineTask);
            return ui.showTaskOutput(deadlineTask, tasks.getSize());

        case "event":
            checkTaskDesc(inputs);
            String[] eventDesc = parseEventDesc(inputs[1]);

            Task eventTask = new Event(eventDesc[0],
                    handleDateTime(eventDesc[1]).toString(),
                    handleDateTime(eventDesc[2]).toString());
            tasks.addToTasks(eventTask);
            return ui.showTaskOutput(eventTask, tasks.getSize());

        case "delete":
            int taskNo = Integer.parseInt(inputs[1]) - 1;
            return tasks.deleteTask(taskNo);

        case "find":
            ArrayList<Task> taskList = tasks.getTasks();
            ArrayList<Task> output = new ArrayList<>();
            for (Task task : taskList) {
                if (task.toString().contains(inputs[1])) {
                    output.add(task);
                }
            }
            return ui.filter(output);

        case "bye":
            return ui.showExit();

        default:
            throw new InvalidTaskException();
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
    public static LocalDateTime handleDateTime(String dateTime) throws InvalidDateTimeException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Split the event desc
     *
     * @param desc the desc of an event task
     * @return a string array with all the parts of an event desc
     */
    public static String[] parseEventDesc(String desc) {
        String[] eventDesc = new String[3];
        eventDesc[0] = desc.split(" /from ")[0];
        eventDesc[1] = desc.split(" /from ")[1].split(" /to ")[0];
        eventDesc[2] = desc.split(" /from ")[1].split(" /to ")[1];
        return eventDesc;
    }
}
