package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mma");
    protected LocalDateTime byTime;

    /**
     * Represents a constructor to make a new Deadline object.
     *
     * @param taskName Name of the task.
     * @param byTime Deadline of the task.
     */
    public Deadline(String taskName, LocalDateTime byTime) {
        super(taskName);
        this.byTime = byTime;
    }

    /**
     * Represents a onstructor for a new Deadline object used for loading from save file.
     *
     * @param taskName Name of the task.
     * @param byTime Deadline of the task.
     * @param isDone Status of the task.
     */
    public Deadline(String taskName, LocalDateTime byTime, boolean isDone) {
        super(taskName);
        this.byTime = byTime;
        this.isDone = isDone;
    }

    @Override
    public String[] encode() {
        String[] res = new String[]{"D", this.getStatusIcon(), this.taskName, this.byTime.toString()};
        return res;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byTime.format(DATETIME_FORMAT) + ")";
    }
}
