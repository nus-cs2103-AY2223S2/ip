package utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import data.TaskManager;
import errors.DukeInvalidCommandException;
import errors.DukeRuntimeException;
import task.Deadline;
import task.Event;
import task.ToDo;
import timeslot.FreeTimeBlock;
import ui.Format;
import ui.Response;

/**
 * Responsible for the high-level parsing of user inputs and delegating relevant actions
 * @author Nicholas Lee
 */
public class Parser {

    private final TaskManager taskManager;

    public Parser(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * This method is used to parse user input and obtain an integer representing the
     * user's selection of an item from the task list.
     *
     * @param input the raw input string provided by the user
     * @return an integer representing the user's selection of an item from the task list
     * @throws DukeInvalidCommandException if the input provided by the user could not be parsed into an integer
     */
    public int getSelection(String input) throws DukeInvalidCommandException {
        String[] segments = input.split(" ", 2);
        int result;
        try {
            result = Integer.parseInt(segments[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.INVALID_COMMAND.toString());
        }
        return result;
    }

    /**
     * This method is used to parse user input and obtain the user's search query
     *
     * @param input the raw input string provided by the user
     * @return a String representing the user's query of a task
     * @throws DukeInvalidCommandException if the input provided by the user could not be parsed
     */
    public String getSearchKeyword(String input) throws DukeInvalidCommandException {
        String[] segments = input.split(" ", 2);
        String result;
        try {
            result = segments[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.INVALID_COMMAND.toString());
        }
        return result;
    }


    /**
     * This method is used to parse user input and obtain the user's exact desired free time in seconds
     *
     * @param input the raw input number (in hours) as string provided by the user
     * @return an integer representing the number of free seconds the user is requesting
     * @throws DukeInvalidCommandException if the input provided by the user could not be parsed
     */
    public int getDesiredFreeTime(String input) throws DukeInvalidCommandException {
        String[] segments = input.split(" ", 2);
        int result;
        try {
            result = Integer.parseInt(segments[1]) * 3600;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.INVALID_COMMAND.toString());
        }
        return result;
    }

    /**
     * This method is used to parse user input and obtain the bot keyword provided by the user.
     *
     * @param input the input string provided by the user
     * @return a string representing the bot keyword provided by the user
     */
    public String getAction(String input) {
        String[] segments = input.split(" ", 2);
        return segments[0];
    }

    /**
     * This method is used to create an event by parsing user input and creating a new Event object.
     *
     * @param input the input string provided by the user
     * @return a string indicating the bot response to the event creation outcome (either success or failure)
     */
    public String createEvent(String input) {
        String output;
        String details;
        LocalDateTime startDate;
        LocalDateTime endDate;
        HashMap<String, String> parsedDetails;
        try {
            parsedDetails = Event.parse(input);
            details = parsedDetails.get("details");
            startDate = Utility.parseDateTime(parsedDetails.get("from"));
            endDate = Utility.parseDateTime(parsedDetails.get("to"));
        } catch (DukeRuntimeException e) {
            return e.getMessage();
        }

        if (startDate.compareTo(endDate) > 0) {
            return Response.START_LATER_THAN_END.toString();
        }

        Event event = new Event(details, startDate, endDate);
        taskManager.addTask(event);
        output = Response.EVENT_ADDED + "\n" + Format.displayTasks(false, taskManager);
        return output;
    }

    /**
     * This method is used to create an event by parsing user input and creating a new Deadline object.
     *
     * @param input the input string provided by the user
     * @return a string indicating the bot response to the deadline creation outcome (either success or failure)
     */
    public String createDeadline(String input) {
        String output;
        String details;
        LocalDateTime deadlineDate;
        HashMap<String, String> parsedDetails;
        try {
            parsedDetails = Deadline.parse(input);
            details = parsedDetails.get("details");
            deadlineDate = Utility.parseDateTime(parsedDetails.get("deadline"));
        } catch (DukeRuntimeException e) {
            return e.getMessage();
        }
        Deadline deadline = new Deadline(details, deadlineDate);
        taskManager.addTask(deadline);
        output = Response.DEADLINE_ADDED + "\n" + Format.displayTasks(false, taskManager);
        return output;
    }


    /**
     * This method is used to create an event by parsing user input and creating a new to-do object.
     *
     * @param input the input string provided by the user
     * @return a string indicating the bot response to the to-do creation outcome (either success or failure)
     */
    public String createToDo(String input) {
        String output;
        HashMap<String, String> parsedDetails;
        try {
            parsedDetails = ToDo.parse(input);
        } catch (DukeRuntimeException e) {
            return e.getMessage();
        }
        ToDo todo = new ToDo(parsedDetails.get("details"));
        taskManager.addTask(todo);
        output = Response.TODO_ADDED + "\n" + Format.displayTasks(false, taskManager);
        return output;
    }

    /**
     * This method executes a bot action to mark a task as completed or not based on user input.
     *
     * @param isCompleted a boolean indicating whether the task should be marked as completed or not completed
     * @param input the input string provided by the user
     * @return a string indicating the bot response of the task marking, such as success or failure
     */
    public String markTaskEvent(boolean isCompleted, String input) {
        String output;
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
            output = Response.COMPLETED_TASK + "\n" + Format.displayTasks(false, taskManager);
            return output;
        }
        output = Response.INCOMPLETE_TASK + "\n" + Format.displayTasks(false, taskManager);
        return output;
    }

    /**
     * This method executes a bot action to delete a task based on user input.
     *
     * @param input the input string provided by the user
     * @return a string indicating the bot response of the task deletion, such as success or failure
     */
    public String deleteTaskEvent(String input) {
        String output;
        try {
            int selectionIndex = getSelection(input);
            taskManager.deleteTask(selectionIndex);
        } catch (DukeInvalidCommandException e) {
            return e.getMessage();
        }
        output = Response.TASK_DELETED + "\n" + Format.displayTasks(false, taskManager);
        return output;
    }


    /**
     * Gets a preview of filtered tasks matching an input string.
     * @param input the input string to search for a task event
     * @return bot response including a string representation of the filtered tasks that matched the input,
     *         or an error message if the input is invalid
     */
    public String findTaskEvent(String input) {
        String keyword;
        try {
            keyword = getSearchKeyword(input);
        } catch (DukeInvalidCommandException e) {
            return e.getMessage();
        }
        TaskManager taskView = taskManager.filterTasks(keyword);
        return Format.displayFilteredTasks(taskView);
    }

    /**
     * Finds the earliest free time block that fits the user's desired free time
     * @param input the user's input specifying the desired free time
     * @return a String containing a description of the earliest free time block that fits the desired free time
     */
    public String findFreeTimeEvent(String input) {
        int desiredFreeTime;
        String output;
        try {
            desiredFreeTime = getDesiredFreeTime(input);
        } catch (DukeInvalidCommandException e) {
            return e.getMessage();
        }
        ArrayList<FreeTimeBlock> freeTimes = taskManager.getFreeTimes(desiredFreeTime);

        if (freeTimes.isEmpty()) {
            output = Response.NO_FREE_SLOTS.toString();
            return output;
        }
        output = freeTimes.get(0).describeSelf();
        return output;
    }


    /**
     * This method processes user input and delegates the corresponding actions by executing specific bot actions.
     *
     * @param input the input string provided by the user
     * @return a string indicating the bot response of the user input processing
     */
    public String processInput(String input) {

        String action = getAction(input);
        String output;

        switch (action) {
        case "list":

            output = Format.displayTasks(true, taskManager);
            break;

        case "mark":
            output = markTaskEvent(true, input);
            break;

        case "unmark":

            output = markTaskEvent(false, input);
            break;

        case "delete":

            output = deleteTaskEvent(input);
            break;

        case "event":

            output = createEvent(input);
            break;

        case "to-do":

            output = createToDo(input);
            break;

        case "deadline":

            output = createDeadline(input);
            break;

        case "find":
            output = findTaskEvent(input);
            break;

        case "free":
            output = findFreeTimeEvent(input);
            break;

        case "help":
            output = Response.WELCOME_HELP.toString();
            break;

        default:
            output = Response.DEFAULT.toString();
            break;
        }
        assert output != null;
        return output;
    }

}
