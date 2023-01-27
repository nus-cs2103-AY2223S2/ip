package duke.helper;

import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Parser {
    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

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
            tasks.addToTasks(new Todo(inputs[1]));
            tasks.handleTaskOutput();
            break;

        case "deadline":
            checkTaskDesc(inputs);
            tasks.addToTasks(Deadline.createDeadline(inputs[1]));
            tasks.handleTaskOutput();
            break;

        case "event":
            checkTaskDesc(inputs);
            tasks.addToTasks(Event.createEvent(inputs[1]));
            tasks.handleTaskOutput();
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

    public static boolean checkTaskDesc(String[] splitStr) throws EmptyTaskException {
        if (splitStr.length == 1) {
            throw new EmptyTaskException(splitStr[0]);
        }
        return true;
    }

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
}
