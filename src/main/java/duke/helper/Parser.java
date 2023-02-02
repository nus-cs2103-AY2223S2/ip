package duke.helper;

import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidTaskException;
import duke.storage.FileSystem;
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
    private FileSystem fileSystem;

    public Parser(TaskList tasks, Ui ui, FileSystem fileSystem) {
        this.tasks = tasks;
        this.ui = ui;
        this.fileSystem = fileSystem;
    }

    /**
     * Handles the input according to the type of the task
     */
    public String parseInputs(String desc) throws DukeException, IOException {
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
            return Ui.showTaskOutput(toDoTask, tasks.getTasks().size());

        case "deadline":
            checkTaskDesc(inputs);
            String[] deadlineDesc = inputs[1].split(" /by ");
            Task deadlineTask = new Deadline(deadlineDesc[0], deadlineDesc[1]);
            tasks.addToTasks(deadlineTask);
            return Ui.showTaskOutput(deadlineTask, tasks.getTasks().size());

        case "event":
            checkTaskDesc(inputs);
            String[] eventDesc = parseEventDesc(inputs[1]);
            Task eventTask = new Event(eventDesc[0], eventDesc[1], eventDesc[2]);
            tasks.addToTasks(eventTask);
            return Ui.showTaskOutput(eventTask, tasks.getTasks().size());

        case "delete":
            int taskNo = Integer.parseInt(inputs[1]) - 1;
            return tasks.deleteTask(taskNo).toString();

        case "find":
            ArrayList<Task> taskList = tasks.getTasks();
            ArrayList<Task> output = new ArrayList<>();
            for (Task task : taskList) {
                if (task.toString().contains(inputs[1])) {
                    output.add(task);
                }
            }
            return Ui.filter(output);

        case "bye":
            fileSystem.updateFile(tasks);
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
            String[] period = dateTime.split("/", 3);
            String[] yearTime = period[2].split(" ");
            int year = Integer.parseInt(yearTime[0]);
            int month = Integer.parseInt(period[1]);
            int day = Integer.parseInt(period[0]);
            int hour = Integer.parseInt(yearTime[1].substring(0, 2));
            int min = Integer.parseInt(yearTime[1].substring(2));
            return LocalDateTime.of(year, month, day, hour, min);
        } catch (NumberFormatException | IndexOutOfBoundsException | DateTimeException e) {
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
