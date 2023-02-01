package duke.helper;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
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

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Handles the input according to the type of the task
     */
    public void parseInputs(String[] inputs) throws DukeException, IOException {
        String type = inputs[0];

        switch (type) {
        case "list":
            tasks.outputList();
            break;

        case "mark":
            tasks.mark(true, inputs[1]);
            break;

        case "unmark":
            tasks.mark(false, inputs[1]);
            break;

        case "todo":
            checkTaskDesc(inputs);
            Task toDoTask = new ToDo(inputs[1], false);
            tasks.addToTasks(toDoTask);
            Ui.showTaskOutput(toDoTask, tasks.getTasks().size());
            break;

        case "deadline":
            checkTaskDesc(inputs);
            String[] deadlineDesc = inputs[1].split(" /by ");
            Task deadlineTask = new Deadline(deadlineDesc[0], deadlineDesc[1], false);
            tasks.addToTasks(deadlineTask);
            Ui.showTaskOutput(deadlineTask, tasks.getTasks().size());
            break;

        case "event":
            checkTaskDesc(inputs);
            String[] eventDesc = parseEventDesc(inputs[1]);
            Task eventTask = new Event(eventDesc[0], eventDesc[1], eventDesc[2], false);
            tasks.addToTasks(eventTask);
            Ui.showTaskOutput(eventTask, tasks.getTasks().size());
            break;

        case "delete":
            int taskNo = Integer.parseInt(inputs[1]) - 1;
            tasks.deleteTask(taskNo);
            break;

        case "find":
            ArrayList<Task> taskList = tasks.getTasks();
            ArrayList<Task> output = new ArrayList<>();
            for (Task task : taskList) {
                if (task.toString().contains(inputs[1])) {
                    output.add(task);
                }
            }
            Ui.filter(output);
            break;

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
