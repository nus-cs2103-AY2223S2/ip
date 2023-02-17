package duke.task;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

import duke.parser.TimeHandler;

/**
 * A FixedDurationTask class that encapsulates the information of a task with a fixed duration.
 */
public class FixedDurationTask extends DukeTask {
    private static final String STORAGE_FORMAT = "[F] | %s | %s | %s";
    private static final String PRINT_FORMAT = "[F]%s ( duration: %s )";
    private final Duration duration;

    /**
     * Constructs a FixedDurationTask object with the given task information and duration.
     *
     * @param info The task information.
     * @param duration The duration of the task.
     */
    public FixedDurationTask(String info, Duration duration) {
        super(info, TaskType.FIXED_DURATION);
        this.duration = duration;
    }

    /**
     * Returns the duration of the task.
     *
     * @return The duration of the task.
     */
    public Duration getDuration() {
        return this.duration;
    }

    /**
     * Returns the task information in the format suitable for storage.
     *
     * @return The task information in the storage format.
     */
    @Override
    public String storageString() {
        // Format the task status, task information, and duration into a single string
        String isCompleted = this.getStatus() ? "[X]" : "[ ]";
        return String.format(STORAGE_FORMAT, isCompleted,
                this.getInformation().trim(), this.duration.toString());
    }

    /**
     * Determines whether the task matches the given date.
     *
     * @param date The date to check against.
     * @return false, as FixedDurationTasks do not have a date.
     */
    @Override
    public boolean matchesDate(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format(PRINT_FORMAT, super.toString(),
                TimeHandler.humanReadableFormat(this.duration));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof FixedDurationTask)) {
            return false;
        }

        FixedDurationTask fixedObj = (FixedDurationTask) obj;
        return Objects.equals(this.getInformation(), fixedObj.getInformation())
                && Objects.equals(this.duration, fixedObj.duration)
                && this.getStatus() == fixedObj.getStatus();
    }
}
