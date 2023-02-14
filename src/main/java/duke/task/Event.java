package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task called event with a starting from time and ending to time.
 */
public class Event extends Task {

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mma");
    protected LocalDateTime fromTime;
    protected LocalDateTime toTime;

    /**
     * Represents a constructor to make a new Event object.
     *
     * @param taskName Name of the task.
     * @param fromTime Starting time of the event.
     * @param toTime Ending time of the event.
     */
    public Event(String taskName, LocalDateTime fromTime, LocalDateTime toTime) {
        super(taskName);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Represents a constructor for a new Event object used for loading from save file.
     *
     * @param taskName Name of the task.
     * @param fromTime Starting time of the event.
     * @param toTime Ending time of the event.
     */
    public Event(String taskName, LocalDateTime fromTime, LocalDateTime toTime, boolean isDone) {
        super(taskName);
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.isDone = isDone;
    }


    @Override
    public String[] encode() {
        String[] res = new String[]{"E", this.getStatusIcon(), this.taskName, this.fromTime.toString(),
                this.toTime.toString()};
        return res;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromTime.format(DATETIME_FORMAT) + " to: "
                + toTime.format(DATETIME_FORMAT) + ")";
    }
}
