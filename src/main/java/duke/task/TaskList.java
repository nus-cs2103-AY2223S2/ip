package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.parser.Parser;

/**
 * Represents a TaskList that can hold {@link Task} objects.
 */
public class TaskList {
    private final ArrayList<Task> TASKS;

    /**
     * Creates a task-list object with the given list of Task objects.
     *
     * @param taskList The list of Task objects to be contained in the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        TASKS = taskList;
    }

    /**
     * Adds a to-do task from the given user command to the TaskList.
     *
     * @param userCommand The user command that contains the task description.
     * @throws DukeException If the task description is missing.
     */
    public String addToDo(String userCommand) throws DukeException {

        String[] userCommandParts = userCommand.split(" ", 2);
        if (userCommandParts.length < 2) {
            throw new DukeInvalidArgumentException("Please enter task description");
        }

        String taskDescription = userCommandParts[1].trim();
        ToDo toDo = new ToDo(taskDescription);
        TASKS.add(toDo);
        return ("Got it. I've added this task:\n\t" + toDo
                + "\nNow you have " + TASKS.size() + " task(s) in the list.");
    }

    /**
     * Adds a deadline task from the given user command to the TaskList.
     *
     * @param userCommand The user command that contains the task description
     *                    and the due-date.
     * @throws DukeException If the command has an invalid format.
     */
    public String addDeadline(String userCommand) throws DukeException {

        String[] userCommandParts = userCommand.split(" /by", 2);
        String description = userCommandParts[0].replace("deadline", "").trim();

        if (description.isEmpty()) {
            throw new DukeInvalidArgumentException("Please enter task description!");
        }

        if (userCommandParts.length < 2) {
            throw new DukeInvalidArgumentException("Invalid format!\n"
                    + "Use `deadline {description} /by {due date}`");
        }

        if (userCommandParts[1].trim().isEmpty()) {
            throw new DukeInvalidArgumentException("Please provide due date after `/by` parameter");
        }

        LocalDateTime dueDate = Parser.parseDateTime(userCommandParts[1].trim()) ;
        Deadline deadline = new Deadline(description, dueDate);
        TASKS.add(deadline);

        return ("Got it. I've added this task:\n\t" + deadline
                + "\nNow you have " + TASKS.size() + " task(s) in the list.");
    }

    /**
     * Adds an event task from the given user command to the TaskList.
     *
     * @param userCommand The user command that contains the task description,
     *                    the start date-time, and the end date-time.
     * @throws DukeException If the command has an invalid format.
     */
    public String addEvent(String userCommand) throws DukeException {

        String[] userCommandParts = userCommand.split(" /from", 2);
        String description = userCommandParts[0].replace("event", "").trim();

        if (description.isEmpty()) {
            throw new DukeInvalidArgumentException("Please enter task description!");
        }

        if (userCommandParts.length < 2) {
            throw new DukeInvalidArgumentException("Invalid format!\n"
                    + "Use `event {description} /from {start date/time} /to {end date/time}`");
        }

        String[] timeParts = userCommandParts[1].split(" /to", 2);

        boolean isEmptyStartDateTime = timeParts.length < 1 || timeParts[0].trim().isEmpty();
        if (isEmptyStartDateTime) {
            throw new DukeInvalidArgumentException("Please provide start date/time after `/from` parameter");
        }

        boolean isEmptyEndDateTime = timeParts.length < 2 || timeParts[1].trim().isEmpty();
        if (isEmptyEndDateTime) {
            throw new DukeInvalidArgumentException("Please provide end date/time after `/to` parameter");
        }

        LocalDateTime startDateTime = Parser.parseDateTime(timeParts[0].trim());
        LocalDateTime endDateTime = Parser.parseDateTime(timeParts[1].trim());

        if (!endDateTime.isAfter(startDateTime)) {
            throw new DukeInvalidArgumentException("The end date/time should be after the start date/time");
        }

        Event event = new Event(description, startDateTime, endDateTime);
        TASKS.add(event);

        return ("Got it. I've added this task:\n\t" + event
                + "\nNow you have " + TASKS.size() + " task(s) in the list.");
    }

    /**
     * Prints the tasks in the TaskList according to
     * their string representation in an ordered list.
     */
    public String printTaskList() {

        StringBuilder result = new StringBuilder();

        if (TASKS.size() == 0) {
            result.append("There are no tasks in your Task List!");
        } else {
            result.append("Your Tasks:\n");
            for (int i = 0; i < TASKS.size(); i++) {
                result.append(i + 1)
                        .append(". ")
                        .append(TASKS.get(i))
                        .append("\n");
            }
        }

        return result.toString();
    }

    /**
     * Marks the task specified in the user command as done in the TaskList.
     *
     * @param userCommand The user command that contains the index (1-indexed)
     *                    of the task in the TaskList to be marked as done.
     * @throws DukeException If the given index is out of bounds or invalid.
     */
    public String markTask(String userCommand) throws DukeException {
        try {
            String[] userCommandParts = userCommand.split(" ");
            if (userCommandParts.length != 2) {
                throw new DukeInvalidArgumentException("Use format: mark {task no.}");
            }

            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            if (TASKS.size() == 0) {
                return "There are no tasks in your Task List!";

            } else if (0 <= taskIndex && taskIndex < TASKS.size()) {
                Task task = TASKS.get(taskIndex);
                task.setDone();
                return ("Task marked as completed\n" + task);

            } else {
                throw new DukeInvalidArgumentException("Please provide a valid Task number\n"
                        + "You have " + TASKS.size() + " task(s) in your Task List");
            }

        } catch (NumberFormatException exception) {
            throw new DukeInvalidArgumentException("Please provide a valid Task number");
        }
    }

    /**
     * Marks the task specified in the user command as not done in the TaskList.
     *
     * @param userCommand The user command that contains the index (1-indexed)
     *                    of the task in the TaskList to be marked as not done.
     * @throws DukeException If the given index is out of bounds or invalid.
     */
    public String unmarkTask(String userCommand) throws DukeException {
        try {
            String[] userCommandParts = userCommand.split(" ");
            if (userCommandParts.length != 2) {
                throw new DukeInvalidArgumentException("Use format: unmark {task no.}");
            }

            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            if (TASKS.size() == 0) {
                return "There are no tasks in your Task List!";

            } else if (0 <= taskIndex && taskIndex < TASKS.size()) {
                Task task = TASKS.get(taskIndex);
                task.setNotDone();
                return ("Task marked as not completed\n" + task);

            } else {
                throw new DukeInvalidArgumentException("Please provide a valid Task number\n"
                        + "You have " + TASKS.size() + " task(s) in your Task List");
            }

        } catch (NumberFormatException exception) {
            throw new DukeInvalidArgumentException("Please provide a valid Task number");
        }

    }

    /**
     * Deletes the task specified in the user command from the TaskList.
     *
     * @param userCommand The user command that contains the index (1-indexed)
     *                    of the task in the TaskList to be deleted.
     * @throws DukeException If the given index is out of bounds or invalid.
     */
    public String deleteTask(String userCommand) throws DukeException {
        try {
            String[] userCommandParts = userCommand.split(" ");
            if (userCommandParts.length != 2) {
                throw new DukeInvalidArgumentException("Use format: delete {task no.}");
            }

            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            if (TASKS.size() == 0) {
                return "There are no tasks in your Task List!";
            } else if (0 <= taskIndex && taskIndex < TASKS.size()) {
                Task task = TASKS.get(taskIndex);
                TASKS.remove(taskIndex);

                return ("Noted. I've deleted this task:\n\t" + task
                        + "\nNow you have " + TASKS.size() + " task(s) in the list.");
            } else {
                throw new DukeInvalidArgumentException("Please provide a valid Task number\n"
                        + "You have " + TASKS.size() + " task(s) in your Task List");
            }

        } catch (NumberFormatException exception) {
            throw new DukeInvalidArgumentException("Please provide a valid Task number");
        }
    }

    /**
     * Returns the TaskList in its current state.
     *
     * @return The TaskList containing the Task objects
     */
    public ArrayList<Task> getTaskList() {
        return TASKS;
    }

    public String findTask(String userCommand) throws DukeException {
        String[] userCommandParts = userCommand.split(" ", 2);
        if (userCommandParts.length != 2) {
            throw new DukeInvalidArgumentException("Please enter search keyword");
        }

        String keyword = userCommandParts[1];
        ArrayList<Task> results = new ArrayList<>();

        for (Task task : TASKS) {
            if (task.getDescription().contains(keyword)) {
                results.add(task);
            }
        }

        if (results.size() == 0) {
            return ("No search results for \"" + keyword + "\" :(");
        } else {
            StringBuilder result = new StringBuilder("Search results for \"" + keyword + "\":\n");
            for (int i = 0; i < results.size(); i++) {
                result.append(i + 1)
                        .append(". ")
                        .append(results.get(i))
                        .append("\n");
            }
            return result.toString();
        }
    }
}
