package duke.task;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/** Deals with event tasks. */
public class Event extends Task {

    private final LocalDate from;
    private final LocalDate to;

    /**
     * Generates a new event task.
     *
     * @param description Description of event.
     * @param from Start time of event.
     * @param to End time of event.
     * @param isDone Status of event.
     */
    public Event(String description, String from, String to, boolean... isDone) throws DukeException {
        super(description, isDone.length > 0 && isDone[0]);
        try {
            this.from = LocalDate.parse(from);
        } catch (DateTimeParseException e) {
            throw DukeException.getErrorTaskTimeFormat("an event", "start time");
        }
        try {
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw DukeException.getErrorTaskTimeFormat("an event", "end time");
        }
    }

    public String getTimeFormat(LocalDate time) {
        return time.format(DateTimeFormatter
                .ofPattern("MMM d yyyy"));
    }

    public String getStartTime() {
        return from.toString();
    }

    public String getEndTime() {
        return to.toString();
    }

    /**
     * Returns task in saved data format.
     *
     * @param delimiter String separating fields.
     * @return Task in saved data format.
     */
    @Override
    public String toSaveData(String delimiter) {
        return "E" + delimiter
                + getStatusIcon()
                + delimiter
                + getDescription()
                + delimiter
                + from
                + delimiter
                + to;
    }

    /**
     * Loads task from given saved data.
     *
     * @param data Saved data of task.
     * @param delimiter String separating fields.
     * @return New task.
     * @throws DukeException If saved data is missing some fields.
     */
    public static Event load(String data, String delimiter) throws DukeException {
        try {
            String[] fields = data.split(delimiter, 3);
            String taskType = fields[0];
            boolean status = fields[1].equals("X");
            String[] taskFields = fields[2].split(delimiter, 3);
            String description = taskFields[0].trim();
            String from = taskFields[1].trim();
            String to = taskFields[2].trim();

            assert taskType.equals("E") : "Task is of the wrong type";

            return new Event(description, from, to, status);
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.getErrorTaskLoadMissingField();
        }
    }

    /**
     * Generates new event task from given user input.
     *
     * @param input User's input.
     * @return New event task.
     * @throws DukeException If input is missing some fields.
     */
    public static Event generate(String input) throws DukeException {
        try {
            String[] fields = input.trim().split(" ", 2);
            String[] taskFields = fields[1].split(" /from | /to ", 3);
            String description = taskFields[0].trim();
            String from = taskFields[1].trim();
            String to = taskFields[2].trim();

            assert (fields[0].trim()
                    .equalsIgnoreCase("event"))
                    : "The given input is of the wrong task type";

            return new Event(description, from, to);
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.getErrorTaskMissingField("Event");
        }
    }
    // event test /from 2023-02-01 /to 2023-03-01
    /**
     * Compares this task to the specified task.
     *
     * @param task The task to compare this task against.
     * @return true if the given task is equivalent to this task, false otherwise.
     */
    public boolean equals(Task task) {
        boolean isSameClass = task.getClass().equals(getClass());
        if (!isSameClass) {
            return false;
        }

        boolean isSameStartTime = ((Event) task).from.isEqual(from);
        boolean isSameEndTime = ((Event) task).to.isEqual(to);

        return super.equals(task)
                && isSameStartTime
                && isSameEndTime;
    }

    /**
     * Returns the task's details in string format.
     *
     * @return Task's details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + getTimeFormat(from)
                + " to: " + getTimeFormat(to) + ")";
    }
}
