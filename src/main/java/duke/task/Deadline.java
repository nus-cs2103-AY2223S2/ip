package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/** Deals with deadline tasks. */
public class Deadline extends Task {

    private final LocalDate by;

    /**
     * Generates a new deadline task.
     *
     * @param description Description of task.
     * @param by Deadline of task.
     * @param isDone Status of task.
     */
    public Deadline(String description, String by, boolean... isDone) throws DukeException {
        super(description.trim(), isDone.length > 0 && isDone[0]);
        try {
            this.by = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("The deadline of a deadline task must be in the format <YYYY-MM-DD>.");
        }
    }

    public String getTimeFormat(LocalDate time) {
        return time.format(DateTimeFormatter
                .ofPattern("MMM d yyyy"));
    }

    public String getDeadline() {
        return by.toString();
    }

    /**
     * Returns task in saved data format.
     *
     * @param delimiter String separating fields.
     * @return Task in saved data format.
     */
    @Override
    public String toSaveData(String delimiter) {
        return "D" + delimiter
                + getStatusIcon()
                + delimiter
                + getDescription()
                + delimiter
                + by;
    }

    /**
     * Loads task from given saved data.
     *
     * @param data Saved data of task.
     * @param delimiter String separating fields.
     * @return New deadline task.
     * @throws DukeException If saved data is missing some fields.
     */
    public static Deadline load(String data, String delimiter) throws DukeException {
        try {
            String[] fields = data.split(delimiter, 3);
            String taskType = fields[0];
            boolean status = fields[1].equals("X");
            String[] taskFields = fields[2].split(delimiter, 2);
            String description = taskFields[0].trim();
            String by = taskFields[1].trim();

            assert taskType.equals("D") : "Task is of the wrong type";

            return new Deadline(description, by, status);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Saved data is missing some fields");
        }
    }

    /**
     * Generates new deadline task from given user input.
     *
     * @param input User's input.
     * @return New deadline task.
     * @throws DukeException If input is missing some fields.
     */
    public static Deadline generate(String input) throws DukeException {
        try {
            String[] fields = input.trim().split(" ", 2);
            String[] taskFields = fields[1].split(" /by ", 2);
            String description = taskFields[0].trim();
            String by = taskFields[1].trim();

            assert (fields[0].trim()
                    .equalsIgnoreCase("deadline"))
                    : "The given input is of the wrong task type";

            return new Deadline(description, by);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Deadline task is missing some fields.");
        }
    }

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

        boolean isSameDeadline = ((Deadline) task).by.equals(by);

        return super.equals(task) && isSameDeadline;
    }

    /**
     * Returns the task's details in string format.
     *
     * @return Task's details.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + getTimeFormat(by) + ")";
    }
}
