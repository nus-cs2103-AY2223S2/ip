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
    private final ArrayList<Task> TASK_LIST;

    /**
     * Creates a task-list object with the given list of Task objects.
     *
     * @param taskList The list of Task objects to be contained in the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        TASK_LIST = taskList;
    }

    /**
     * Adds a to-do task from the given user command to the TaskList.
     *
     * @param userCommand The user command that contains the task description.
     * @throws DukeException If the task description is missing.
     */
    public void addToDo(String userCommand) throws DukeException {

        String[] userCommandParts = userCommand.split(" ", 2);
        if (userCommandParts.length < 2) {
            throw new DukeInvalidArgumentException("Please enter task description");
        }

        String taskDescription = userCommandParts[1].trim();
        ToDo toDo = new ToDo(taskDescription);
        TASK_LIST.add(toDo);
        System.out.println("Got it. I've added this task:\n\t" + toDo
                + "\nNow you have " + TASK_LIST.size() + " task(s) in the list.");
    }

    /**
     * Adds a deadline task from the given user command to the TaskList.
     *
     * @param userCommand The user command that contains the task description
     *                    and the due-date.
     * @throws DukeException If the command has an invalid format.
     */
    public void addDeadline(String userCommand) throws DukeException {

        String[] userCommandParts = userCommand.split(" /by", 2);
        String description = userCommandParts[0].replace("deadline", "").trim();

        if (description.isEmpty()) {
            throw new DukeInvalidArgumentException("Please enter task description!");
        }

        if (userCommandParts.length < 2) {
            throw new DukeInvalidArgumentException("Invalid format!\n" +
                    "Use `deadline {description} /by {due date}`");
        }

        if (userCommandParts[1].trim().isEmpty()) {
            throw new DukeInvalidArgumentException("Please provide due date after `/by` parameter");
        }

        LocalDateTime dueDate = Parser.parseDateTime(userCommandParts[1].trim()) ;
        Deadline deadline = new Deadline(description, dueDate);
        TASK_LIST.add(deadline);

        System.out.println("Got it. I've added this task:\n\t" + deadline
                + "\nNow you have " + TASK_LIST.size() + " task(s) in the list.");
    }

    /**
     * Adds an event task from the given user command to the TaskList.
     *
     * @param userCommand The user command that contains the task description,
     *                    the start date-time, and the end date-time.
     * @throws DukeException If the command has an invalid format.
     */
    public void addEvent(String userCommand) throws DukeException {

        String[] userCommandParts = userCommand.split(" /from", 2);
        String description = userCommandParts[0].replace("event", "").trim();

        if (description.isEmpty()) {
            throw new DukeInvalidArgumentException("Please enter task description!");
        }

        if (userCommandParts.length < 2) {
            throw new DukeInvalidArgumentException("Invalid format!\n" +
                    "Use `event {description} /from {start date/time} /to {end date/time}`");
        }

        String[] timeParts = userCommandParts[1].split(" /to", 2);

        if (timeParts.length < 1 || timeParts[0].trim().isEmpty()) {
            throw new DukeInvalidArgumentException("Please provide start date/time after `/from` parameter");
        }

        if (timeParts.length < 2 || timeParts[1].trim().isEmpty()) {
            throw new DukeInvalidArgumentException("Please provide end date/time after `/to` parameter");
        }

        LocalDateTime startDateTime = Parser.parseDateTime(timeParts[0].trim());
        LocalDateTime endDateTime = Parser.parseDateTime(timeParts[1].trim());

        if (!endDateTime.isAfter(startDateTime)) {
            throw new DukeInvalidArgumentException("The end date/time should be after the start date/time");
        }

        Event event = new Event(description, startDateTime, endDateTime);
        TASK_LIST.add(event);

        System.out.println("Got it. I've added this task:\n\t" + event
                + "\nNow you have " + TASK_LIST.size() + " task(s) in the list.");
    }

    /**
     * Prints the tasks in the TaskList according to
     * their string representation in an ordered list.
     */
    public void printTaskList() {
        if (TASK_LIST.size() == 0) {
            System.out.println("There are no tasks in your Task List!");
        } else {
            System.out.println("Your Tasks:");
            for (int i = 0; i < TASK_LIST.size(); i++) {
                System.out.println((i + 1) + ". " + TASK_LIST.get(i));
            }
        }
    }

    /**
     * Marks the task specified in the user command as done in the TaskList.
     *
     * @param userCommand The user command that contains the index (1-indexed)
     *                    of the task in the TaskList to be marked as done.
     * @throws DukeException If the given index is out of bounds or invalid.
     */
    public void markTask(String userCommand) throws DukeException {
        try {
            String[] userCommandParts = userCommand.split(" ");
            if (userCommandParts.length != 2) {
                throw new DukeInvalidArgumentException("Use format: mark {task no.}");
            }

            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            if (TASK_LIST.size() == 0) {
                System.out.println("There are no tasks in your Task List!");

            } else if (0 <= taskIndex && taskIndex < TASK_LIST.size()) {
                Task task = TASK_LIST.get(taskIndex);
                task.setDone();
                System.out.println("Task marked as completed\n" + task);

            } else {
                throw new DukeInvalidArgumentException("Please provide a valid Task number\n" +
                        "You have " + TASK_LIST.size() + " task(s) in your Task List");
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
    public void unmarkTask(String userCommand) throws DukeException {
        try {
            String[] userCommandParts = userCommand.split(" ");
            if (userCommandParts.length != 2) {
                throw new DukeInvalidArgumentException("Use format: unmark {task no.}");
            }

            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            if (TASK_LIST.size() == 0) {
                System.out.println("There are no tasks in your Task List!");

            } else if (0 <= taskIndex && taskIndex < TASK_LIST.size()) {
                Task task = TASK_LIST.get(taskIndex);
                task.setNotDone();
                System.out.println("Task marked as not completed\n" + task);

            } else {
                throw new DukeInvalidArgumentException("Please provide a valid Task number\n" +
                        "You have " + TASK_LIST.size() + " task(s) in your Task List");
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
    public void deleteTask(String userCommand) throws DukeException {
        try {
            String[] userCommandParts = userCommand.split(" ");
            if (userCommandParts.length != 2) {
                throw new DukeInvalidArgumentException("Use format: delete {task no.}");
            }

            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            if (TASK_LIST.size() == 0) {
                System.out.println("There are no tasks in your Task List!");
            } else if (0 <= taskIndex && taskIndex < TASK_LIST.size()) {
                Task task = TASK_LIST.get(taskIndex);
                TASK_LIST.remove(taskIndex);

                System.out.println("Noted. I've deleted this task:\n\t" + task
                        + "\nNow you have " + TASK_LIST.size() + " task(s) in the list.");
            } else {
                throw new DukeInvalidArgumentException("Please provide a valid Task number\n" +
                        "You have " + TASK_LIST.size() + " task(s) in your Task List");
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
        return TASK_LIST;
    }
}
