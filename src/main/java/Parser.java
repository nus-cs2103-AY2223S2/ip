import data.TaskManager;
import errors.DukeInvalidCommandException;
import errors.DukeRuntimeException;
import formatters.Response;
import task.Deadline;
import task.Event;
import task.ToDo;
import java.util.HashMap;

public class Parser {

    private final TaskManager taskManager;

    public Parser(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public int getSelection(String input) throws DukeInvalidCommandException {
        String[] segments = input.split(" ", 2);
        int result;
        try {
            result = Integer.parseInt(segments[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeInvalidCommandException(Response.INVALID_COMMAND.toString());
        }
        return result;
    }

    public String getAction(String input) {
        String[] segments = input.split(" ", 2);
        return segments[0];
    }

    public String createEvent(String input) {
        HashMap<String, String> parsedDetails;
        try {
            parsedDetails = Event.parse(input);
        } catch (DukeRuntimeException e) {
            return e.getMessage();
        }
        Event event = new Event(parsedDetails.get("details"), parsedDetails.get("from"), parsedDetails.get("to"));
        taskManager.addTask(event);
        return Response.EVENT_ADDED.toString();
    }

    public String createDeadline(String input) {
        HashMap<String, String> parsedDetails;
        try {
            parsedDetails = Deadline.parse(input);
        } catch (DukeRuntimeException e) {
            return e.getMessage();
        }
        Deadline deadline = new Deadline(parsedDetails.get("details"), parsedDetails.get("deadline"));
        taskManager.addTask(deadline);
        return Response.DEADLINE_ADDED.toString();
    }

    public String createToDo(String input) {
        HashMap<String, String> parsedDetails;
        try {
            parsedDetails = ToDo.parse(input);
        } catch (DukeRuntimeException e) {
            return e.getMessage();
        }
        ToDo todo = new ToDo(parsedDetails.get("details"));
        taskManager.addTask(todo);
        return Response.TODO_ADDED.toString();
    }

    public String markTaskEvent(boolean isCompleted, String input) {
        try {
            int selectionIndex = getSelection(input);
            if (isCompleted) {
                taskManager.markTaskComplete(selectionIndex);
            } else {
                taskManager.markTaskIncomplete(selectionIndex);
            }
        } catch (DukeInvalidCommandException e) {
            return e.getMessage();
        }

        if (isCompleted) {
            return Response.COMPLETED_TASK.toString();
        }
        return Response.INCOMPLETE_TASK.toString();
    }

    public String deleteTaskEvent(String input) {
        try {
            int selectionIndex = getSelection(input);
            taskManager.deleteTask(selectionIndex);
        } catch (DukeInvalidCommandException e) {
            return e.getMessage();
        }
        return Response.TASK_DELETED.toString();
    }


    public String processInput(String input) {

        String action = getAction(input);
        String output = Response.DEFAULT.toString();

        switch (action) {
            case "list":
                output = taskManager.displayTasks(true);
                break;

            case "mark":

                output = markTaskEvent(true, input);
                output += "\n" + taskManager.displayTasks(false);
                break;
            case "unmark":

                output = markTaskEvent(false, input);
                output += "\n" + taskManager.displayTasks(false);
                break;

            case "delete":

                output = deleteTaskEvent(input);
                output += "\n" + taskManager.displayTasks(false);
                break;

            case "event":

                output = createEvent(input);
                output += "\n" + taskManager.displayTasks(false);
                break;

            case "to-do":

                output = createToDo(input);
                output += "\n" + taskManager.displayTasks(false);
                break;

            case "deadline":

                output = createDeadline(input);
                output += "\n" + taskManager.displayTasks(false);
                break;
        }
        return output;
    }

}
