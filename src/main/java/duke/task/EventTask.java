package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import duke.exception.InvalidInputException;

/**
 * A DeadlineTask that encapsulates the information and starting and ending
 * dates of a Deadline Task.
 */
public class EventTask extends DukeTask {
    private final LocalDate from;
    private final LocalDate to;
    private static final String FORMAT = "[E] | %s %s | %s | %s";

    /**
     * Constructor for EventTask that takes in the information of the task
     * and its starting date and ending date.
     *
     * @param info The information of the task
     * @param from The starting date of the task
     * @param to The ending date of the task
     * @throws InvalidInputException Throws exception when the staring date is after the end date
     */
    public EventTask(String info, LocalDate from, LocalDate to) throws InvalidInputException {
        super(info, TaskType.EVENT);
        this.from = from;
        this.to = to;
        if (from.isAfter(to)) {
            throw new InvalidInputException("☹ OOPS!!! Start date can not be after than the End date");
        }
    }

    /**
     * Returns the starting date of the task
     *
     * @return the starting date of the task
     */
    public LocalDate getStartDate() {
        return this.from;
    }

    /**
     * Returns the ending date of the task
     *
     * @return the ending date of the task
     */
    public LocalDate getEndDate() {
        return this.to;
    }

    /**
     * Returns a string representation of the task in a specific format, indicating whether the task is done or not,
     * the information of the task, start date and end date of event.
     *
     * @return A string representation of the task
     */
    @Override
    public String storageString() {
        String status = this.getStatus() ? "[X] | " : "[ ] | ";
        return String.format(FORMAT, status, this.getInformation(), this.from, this.to);
    }

    /**
     * Returns true if the given date is equal to the start date or end date of the task or between start and end date.
     *
     * @param date The date to check
     * @return true if the date is equal to the start date or end date of the task or between start and end date.
     */
    @Override
    public boolean matchesDate(LocalDate date) {
        return date.isEqual(this.from) || date.isEqual(this.to)
                || (date.isAfter(this.from) && date.isBefore(this.to));
    }

    /**
     * Returns a string representation of the task in a specific format, indicating the task type, whether the task is
     * done or not, the information of the task, start date and end date of event.
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        // Use a constant for the date format pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        // Store the formatted dates in a local variable
        String formattedFrom = this.from.format(formatter);
        String formattedTo = this.to.format(formatter);
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    /**
     * Check whether this event task is equal to the given object
     *
     * @param obj the object to check against
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventTask)) {
            return false;
        }
        EventTask eventObj = (EventTask) obj;

        // Compare the information and the from and to dates
        return Objects.equals(this.getInformation(), eventObj.getInformation())
                && this.getStatus() == eventObj.getStatus()
                && this.from.isEqual(eventObj.from)
                && this.to.isEqual(eventObj.to);
    }
}
