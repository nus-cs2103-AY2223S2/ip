package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.tasks.Deadline;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;

/**
 * Task Assigner to create the correct task type to be stored.
 */
public class TaskAssigner {

    /**
     * First Index of ToDo Description.
     */
    private static Integer TODO_DESCRIPTION_INDEX = 5;

    /**
     * First Index of Deadline Description.
     */
    private static Integer DEADLINE_DESCRIPTION_INDEX = 9;

    /**
     * Maximum size limit of String array when split for Deadline User input.
     */
    private static Integer DEADLINE_MAX_SIZE = 2;

    /**
     * Length of /ny in user input.
     */
    private static Integer LENGTH_OF_BY = 4;

    /**
     * First Index of Event Description.
     */
    private static Integer EVENT_DESCRIPTION_INDEX = 6;

    /**
     * Maximum size limit of String array when split for Event User input.
     */
    private static Integer EVENT_MAX_SIZE = 3;

    /**
     * Length of /from in user input.
     */
    private static Integer LENGTH_OF_FROM = 6;

    /**
     * Length of /to in user input.
     */
    private static Integer LENGTH_OF_TO = 4;

    /**
     * Constant to be minused off from index of the end date of event.
     */
    private static Integer LENGTH_OF_EVENT_END_TIME = 5;

    /**
     * Keywords to creating all types of tasks.
     */
    private static ArrayList<String> taskTypes = new ArrayList<>(List.of("todo", "event", "deadline"));


    /**
     * Creates a Task Assigner for Duke.
     * Responsible for creating either Deadline task, ToDo task or
     * Event task depending on the user's input.
     */
    public TaskAssigner() {}

    /**
     * Creates either Deadline task, ToDo task or
     * Event task depending on the user's input.
     *
     * @param command the user's command.
     * @throws DukeException catch inconsistencies and error in the user's input.
     */
    public Task assignTask(String command) throws DukeException {
        String[] seq = command.split(" ");
        String keyWord = seq[0];

        if (!taskTypes.contains(keyWord)) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }

        if (seq.length == 1) {
            throw new DukeException("OOPS!!! I'm sorry, but the description of a task cannot"
                    + " be empty \n");
        }

        if (keyWord.equals("todo")) {
            return this.assignToDo(command);

        } else if (keyWord.equals("event")) {
            return this.assignEvent(command);
        } else {
            return this.assignDeadline(command);
        }
    }

    /**
     * Creates a ToDo task.
     *
     * @param command the user's command.
     * @return a ToDo task.
     */
    public Task assignToDo(String command) {
        return new ToDos(command.substring(TODO_DESCRIPTION_INDEX));
    }

    /**
     * Creates a Deadline task
     *
     * @param command the user's command.
     * @return a Deadline task.
     * @throws DukeException if Date and Time formatting is incorrect
     */
    public Task assignDeadline(String command) throws DukeException {
        String[] timestampsAsString = command.split("/by ");
        if (timestampsAsString.length != DEADLINE_MAX_SIZE) {
            throw new DukeException("Improper Deadline Format! deadline {desc} /by yyyy-mm-dd hhmm\n");
        }

        try {
            int dateIndex = command.indexOf("/by ") + LENGTH_OF_BY;
            String deadline = command.substring(dateIndex);
            String updatedDeadline = TimeChecker.updateTime(deadline);
            String deadlineDescription = command.substring(DEADLINE_DESCRIPTION_INDEX, dateIndex - LENGTH_OF_BY);
            return new Deadline(deadlineDescription, updatedDeadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Improper Deadline Format! deadline {desc} /by yyyy-mm-dd hhmm\n");
        }
    }

    /**
     * Creates a Event task
     *
     * @param command the user's command.
     * @return a Event task.
     * @throws DukeException if Date and Time formatting is incorrect
     */
    public Task assignEvent(String command) throws DukeException {
        String[] timestampsAsString = command.split("/from | /to ");
        if (timestampsAsString.length != EVENT_MAX_SIZE) {
            throw new DukeException("Improper Event Format! Follow:\n"
                    + "event {desc} /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm\n");
        }
        try {
            int startIndex = command.indexOf("/from") + LENGTH_OF_FROM;
            int endIndex = command.indexOf("/to") + LENGTH_OF_TO;
            String startDate = TimeChecker.updateTime(command.substring(startIndex,
                    endIndex - LENGTH_OF_EVENT_END_TIME));
            String endDate = TimeChecker.updateTime(command.substring(endIndex));
            String eventDescription = command.substring(EVENT_DESCRIPTION_INDEX, startIndex - LENGTH_OF_FROM);
            return new Events(eventDescription, startDate, endDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Improper Event Format! Follow:\n"
                    + "event {desc} /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm\n");
        }
    }
}
