package connor.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Event object that keeps track of the start and end of an event.
 */
public class Event extends Task {

    /** LocalDateTime representing the start date/time */
    private LocalDateTime startDateTime;

    /** LocalDateTime representing the end date/time */
    private LocalDateTime EndDateTime;

    /** String representation of the dateformat for start dateTime */
    private String dataFormat1;

    /** String representation of the dateformat for end dateTime */
    private String dataFormat2;

    /**
     * Constructor to instantiate a new Event object that has a taskName, start and end date/time.
     *
     * @param taskName name of the task.
     * @param taskStart dateTime of when the task start.
     * @param taskEnd endTime of when the task end.
     */
    public Event(String taskName, LocalDateTime taskStart, LocalDateTime taskEnd) {
        super(taskName);
        this.startDateTime = taskStart;
        this.EndDateTime = taskEnd;
        this.dataFormat1 = taskStart.toString();
        this.dataFormat2 = taskEnd.toString();
    }

    /**
     * Constructor to instantiate an Event object from memory.
     *
     * @param taskName name of the task.
     * @param isDone indicates if the event is done.
     * @param startDateFormat date format of the start dateTime.
     * @param endDataFormat date format of the end dateTime.
     */
    public Event(String taskName, Boolean isDone, String startDateFormat, String endDataFormat) {
        super(taskName, isDone);
        this.startDateTime = LocalDateTime.parse(startDateFormat);
        this.EndDateTime = LocalDateTime.parse(endDataFormat);
        this.dataFormat1 = startDateFormat;
        this.dataFormat2 = endDataFormat;
    }

    /**
     * Returns a String in a format that is meant to be stored in the memory with additional start and end dateTime.
     *
     * @return String that represents the Task instance in the memory.
     */
    @Override
    public String dataFormat() {
        return "E|" + super.dataFormat() + "|" + this.dataFormat1 + "|" + this.dataFormat2;
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof Todo) {
            return 1;
        } else if (task instanceof Deadline) {
            return 1;
        }
        Event newTask = (Event) task;
        if (this.startDateTime.equals(newTask.startDateTime)) {
            if (this.EndDateTime.equals(newTask.EndDateTime)) {
                return this.taskName.compareTo(newTask.taskName);
            } else {
                return this.EndDateTime.compareTo(newTask.EndDateTime);
            }
        } else {
            return this.startDateTime.compareTo(newTask.startDateTime);
        }
    }

    /**
     * Returns a String which is a concatenation of task type, if the task is done, taskName, start and end dateTime.
     *
     * @return String representation of the task to be printed.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + formatDateTime(this.startDateTime)
                + " to: "
                + formatDateTime(this.EndDateTime)
                + ")";
    }
}
