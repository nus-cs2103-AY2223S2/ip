package duke.task;

import duke.exception.InvalidInputException;
import duke.parser.ErrorMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * A DeadlineTask that encapsulates the information and starting and ending
 * dates of a Deadline Task.
 */
public class EventTask extends DukeTask {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private static final String STORAGE_FORMAT = "[E] | %s %s | %s | %s";
    private static final String FORMAT = "[E]%s %s ( from: %s to: %s )";

    /**
     * Constructor for EventTask that takes in the information of the task
     * and its starting date and ending date.
     *
     * @param info The information of the task
     * @param from The starting date of the task
     * @param to   The ending date of the task
     * @throws InvalidInputException Throws exception when the staring date is after the end date
     */
    public EventTask(String info, LocalDateTime from, LocalDateTime to) throws InvalidInputException {
        super(info, TaskType.EVENT);
        this.from = from;
        this.to = to;
        if (from.isAfter(to)) {
            throw new InvalidInputException(ErrorMessage.INVALID_FROM_AND_TO_ERROR);
        }
    }

    /**
     * Returns the starting date of the task
     *
     * @return the starting date of the task
     */
    public LocalDateTime getStartDate() {
        return this.from;
    }

    /**
     * Returns the ending date of the task
     *
     * @return the ending date of the task
     */
    public LocalDateTime getEndDate() {
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
        return String.format(STORAGE_FORMAT, status, this.getInformation().trim(), this.from, this.to);
    }

    /**
     * Returns true if the given date is equal to the start date or end date of the task or between start and end date.
     *
     * @param date The date to check
     * @return true if the date is equal to the start date or end date of the task or between start and end date.
     */
    @Override
    public boolean matchesDate(LocalDate date) {
        LocalDate fromDate = this.from.toLocalDate();
        LocalDate toDate = this.to.toLocalDate();
        return date.isEqual(fromDate) || date.isEqual(toDate)
                || (date.isAfter(fromDate) && date.isBefore(toDate));
    }

    /**
     * Returns a string representation of the task in a specific format, indicating the information of the task,
     * start date, and end date of the event.
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        String status = this.getStatus() ? "[X]" : "[ ]";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return String.format(FORMAT, status,
                this.getInformation(), this.from.format(formatter), this.to.format(formatter));
    }

    /**
     * Returns true if the given object is equal to this EventTask.
     * Two EventTasks are considered equal if they have the same information, start date, and end date.
     *
     * @param obj The object to compare to this EventTask
     * @return true if the given object is equal to this EventTask
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        EventTask other = (EventTask) obj;
        return this.getInformation().equals(other.getInformation()) && this.from.equals(other.from)
                && this.to.equals(other.to);
    }

    /**
     * Returns the hash code value of this EventTask
     *
     * @return the hash code value of this EventTask
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getInformation(), this.from, this.to);
    }
}