package duke.task;

import java.time.LocalDateTime;

import duke.datetime.DateTime;

/**
 * Task to represent Event task created by user
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructor class for Event
     *
     * @param name Name of Task
     * @param from Task's starting time
     * @param to Task's ending time
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.startTime = from;
        this.endTime = to;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * @return String version of Event
     */
    @Override
    public String toString() {
        String from = DateTime.getDateTimeString(this.startTime);
        String to = DateTime.getDateTimeString(this.endTime);
        if (super.getIsCompleted()) {
            return String.format("[E][X] %s (from: %s to: %s)\n", super.getTaskName(), from, to);
        } else {
            return String.format("[E][ ] %s (from: %s to: %s)\n", super.getTaskName(), from, to);
        }
    }
}
