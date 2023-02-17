package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.InvalidDateException;

/**
 * Class for Deadline object.
 *
 * @author Andre Lin HuiKai
 * @version CS2103T AY22/23 Semester 2
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor for a Deadline object.
     * @param des Description of the task.
     * @param deadline String format of the deadline of the task.
     * @throws InvalidDateException if the string representation of deadline is not of the correct format.
     */
    public Deadline(String des, String deadline) throws InvalidDateException {
        super(des);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Displays formatted information about the task.
     * @return String informing users details about the task.
     */
    @Override
    public String getStatusIcon() {
        return String.format("[D]%s | BY: %s", super.getStatusIcon(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Encodes information about the task to be saved.
     * @return String representing encoded information about the task.
     */
    @Override
    public String encode() {
        return String.format("%s ### %s ### %s", "deadline", super.encode(), this.deadline);
    }

    /**
     * Checks if the task falls on a given date.
     * @param date The date to check against.
     * @return A boolean value that checks if the task falls on the specified day.
     */
    @Override
    public boolean fallsOnDate(LocalDate date) {
        return this.deadline.equals(date);
    }

    /**
     * Checks if the task is complete and if its deadline is before or on a specified date.
     * @param date the date to check against.
     * @return a boolean value.
     */
    @Override
    public boolean isIncompleteBeforeDate(LocalDate date) {
        return !isComplete()
                && this.deadline.compareTo(date) <= 0;
    }
}
