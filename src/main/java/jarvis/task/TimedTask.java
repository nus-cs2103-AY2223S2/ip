package jarvis.task;

import jarvis.duration.Duration;
import jarvis.exception.command.MissingParameterException;

/**
 * Task class representing a timed (fixed-duration) task.
 */
public class TimedTask extends Task {
    public static final String ID = "TM";

    private final Duration duration;

    public TimedTask(String description, Duration duration) throws MissingParameterException {
        this(description, duration, false);
    }

    /**
     * Constructor for a timed task.
     *
     * @param description Description of the task.
     * @param duration Duration the task takes.
     * @param isDone Whether the task is marked as done.
     * @throws MissingParameterException Not thrown.
     */
    public TimedTask(String description, Duration duration, boolean isDone) throws MissingParameterException {
        super(description, isDone);
        this.duration = duration;
    }

    @Override
    public String serialize() {
        String[] data = {ID, String.valueOf(this.isDone()), this.getDescription(), this.duration.serialize()};
        return String.join(" / ", data);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (duration: %s)", ID, super.toString(), this.duration);
    }
}
