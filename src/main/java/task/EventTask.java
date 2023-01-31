package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for an event type Task.
 */
public class EventTask extends Task {
    private final LocalDate startTime;
    private final LocalDate endTime;

    /**
     * Contructor for an event type Task.
     *
     * @param name Title/name of task.
     * @param startTime Date where task should start.
     * @param endTime Sate where task should end.
     */
    public EventTask(String name, LocalDate startTime, LocalDate endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Contructor for an event type Task when loaded in from hard drive.
     *
     * @param name Title/name of task.
     * @param startTime Date where task should start.
     * @param endTime Sate where task should end.
     * @param isDone True if task has been marked as done, false otherwise.
     */
    public EventTask(String name, LocalDate startTime, LocalDate endTime,Boolean isDone) {
        super(name, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
