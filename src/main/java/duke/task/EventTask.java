package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exception.InvalidInputException;
import duke.parser.ErrorMessage;

/**
 * An Event that encapsulates the information and starting and ending times of an EventTask.
 */
public class EventTask extends DukeTask {
    private static final String STORAGE_FORMAT = "[E] | %s %s | %s | %s";
    private static final String PRINT_FORMAT = "[E]%s %s ( from: %s to: %s )";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructor for EventTask that takes in the information of the task
     * and its starting date and ending date.
     *
     * @param information The information of the task
     * @param startTime The starting date of the task
     * @param endTime   The ending date of the task
     * @throws InvalidInputException Throws exception when the staring date is after the end date
     */
    public EventTask(String information, LocalDateTime startTime, LocalDateTime endTime)
            throws InvalidInputException {
        super(information, TaskType.EVENT);

        this.startTime = startTime;
        this.endTime = endTime;

        if (startTime.isAfter(endTime)) {
            throw new InvalidInputException(ErrorMessage.INVALID_FROM_AND_TO_ERROR);
        }
    }

    /**
     * Returns the starting date of the task.
     *
     * @return the starting date of the task
     */
    public LocalDateTime getStartDate() {
        return this.startTime;
    }

    /**
     * Returns the ending date of the task
     *
     * @return the ending date of the task
     */
    public LocalDateTime getEndDate() {
        return this.endTime;
    }

    /**
     * Returns a string representation of the task in a specific format, indicating whether the task is done or not,
     * the information of the task, start date and end date of event.
     *
     * @return A string representation of the task
     */
    @Override
    public String storageString() {
        String isComplete = this.getStatus() ? "[X] | " : "[ ] | ";
        return String.format(STORAGE_FORMAT, isComplete,
                this.getInformation().trim(), this.startTime, this.endTime);
    }

    /**
     * Returns true if the given date is equal endTime the start date
     * or end date of the task or between start and end date.
     *
     * @param date The date endTime check
     * @return true if the date is equal endTime the start date or end date of the task or between start and end date.
     */
    @Override
    public boolean matchesDate(LocalDate date) {
        LocalDate fromDate = this.startTime.toLocalDate();
        LocalDate toDate = this.endTime.toLocalDate();
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
        return String.format(PRINT_FORMAT, status,
                this.getInformation(), this.startTime.format(formatter), this.endTime.format(formatter));
    }

    /**
     * Returns true if the given object is equal endTime this EventTask.
     * Two EventTasks are considered equal if they have the same information, start date, and end date.
     *
     * @param obj The object endTime compare endTime this EventTask
     * @return true if the given object is equal endTime this EventTask
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
        return this.getInformation().equals(other.getInformation()) && this.startTime.equals(other.startTime)
                && this.endTime.equals(other.endTime);
    }
}
