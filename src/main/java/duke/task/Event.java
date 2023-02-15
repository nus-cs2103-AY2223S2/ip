package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Event: subclass of Task that tracks startDate and endDate
 * <p>
 * author Guo-KeCheng
 */
public class Event extends Task {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * Event class constructor
     * Default isCompleted to be false
     *
     * @param task      Task description as String
     * @param startDate Start date as String
     * @param endDate   End date as String
     * @throws DateTimeParseException When endDate is in the wrong format
     */
    public Event(String task, String startDate, String endDate) throws DateTimeParseException, DukeException {
        super(task, false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        this.startDate = LocalDateTime.parse(startDate, formatter);
        this.endDate = LocalDateTime.parse(endDate, formatter);


        if (startDate.compareTo(endDate) > 0) {
            throw new DukeException("Can't start later than end time");
        }
    }

    /**
     * Event class constructor
     *
     * @param task        Task description as String
     * @param isCompleted Completion status. True -> Completed. False -> Not Completed
     * @param startDate   Start date as String
     * @param endDate     Start date as String
     * @throws DateTimeParseException When endDate is in the wrong format
     */
    public Event(String task, boolean isCompleted, String startDate, String endDate)
            throws DateTimeParseException {
        super(task, isCompleted);
        this.endDate = LocalDateTime.parse(endDate);
        this.startDate = LocalDateTime.parse(startDate);
    }

    /**
     * Get the String representation of the task type
     *
     * @return String representation of the task type
     */
    @Override
    public String getTaskType() {
        return "Event";
    }

    /**
     * Get the String representation of the completion status
     *
     * @return String representation of the completion status
     */
    @Override
    public String getStatus() {
        return String.format("%s", isCompleted());
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Get the String representation of the task description
     *
     * @return String representation of the task description
     */
    @Override
    public String getDescription() {
        return getTask() + " | " + startDate + " | " + endDate;
    }

    /**
     * Override toString method
     *
     * @return String representation of task object including completion status
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(from: " + startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")) + ")";
    }

    @Override
    public int compareTo(Task anotherTask) {
        if (anotherTask instanceof ToDo) {
            return -1;
        } else if (anotherTask instanceof Deadline) {
            return -((Deadline) anotherTask).compare(this.endDate);
        } else if (anotherTask instanceof Event) {
            return -((Event) anotherTask).compare(this.endDate);
        } else {
            return 0; // never reach here
        }
    }


    public int compare(LocalDateTime localDateTime) {
        return this.startDate.compareTo(localDateTime);
    }
}
