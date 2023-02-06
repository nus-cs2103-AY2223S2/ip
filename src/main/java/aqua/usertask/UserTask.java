package aqua.usertask;

import java.time.LocalDateTime;
import java.util.Optional;

import aqua.storage.Reloadable;


/** Represents a task that the user wishes to track. */
public abstract class UserTask implements Reloadable {
    /** Tag of {@code isCompleted} argument when parsing. */
    public static final String TAG_IS_COMPLETE = "completed";

    /** Name of the task. */
    private final String name;


    /**
     * Constructs a task with the specified name.
     *
     * @param name - the name of the task.
     */
    public UserTask(String name) {
        this.name = name;
    }


    /**
     * Constructs an {@code AquaTask} that is a copy of the given task.
     *
     * @param task - the task to construct the new task from.
     */
    public UserTask(UserTask task) {
        this(task.name);
    }


    /**
     * Sets the task's completion status as specified
     *
     * @param isComplete - if the task is completed.
     * @return a task of this task with its completion status set as specified.
     */
    public abstract UserTask mark(boolean isComplete);


    /**
     * Returns if the task is completed.
     *
     * @return {@code true} if the task is completed and {@code false}
     *      otherwise.
     */
    public abstract boolean isComplete();


    /**
     * Returns the name of the task.
     *
     * @return the name of the task.
     */
    public String getName() {
        return name;
    }


    /**
     * Returns the starting time of the task wrapped in an {@code Optional}. If
     * the task has no starting time, {@code Optional.empty} is returned.
     *
     * @return the starting time of the task wrapped in an {@code Optional}.
     */
    public Optional<LocalDateTime> getStart() {
        return Optional.empty();
    }


    /**
     * Returns if the task has started.
     *
     * @return {@code true} if the task has started and {@code false}
     *      otherwise.
     */
    public boolean isStarted() {
        return getStart()
                .map(time -> LocalDateTime.now().isAfter(time))
                .orElse(true);
    }


    /**
     * Returns the ending time of the task wrapped in an {@code Optional}. If
     * the task has no ending time, {@code Optional.empty} is returned.
     *
     * @return the ending time of the task wrapped in an {@code Optional}.
     */
    public Optional<LocalDateTime> getEnd() {
        return Optional.empty();
    }


    /**
     * Returns if the task has ended.
     *
     * @return {@code true} if the task has ended and {@code false} otherwise.
     */
    public boolean isEnded() {
        return getEnd()
                .map(time -> LocalDateTime.now().isAfter(time))
                .orElse(false);
    }


    /**
     * Returns the String representation of the task.
     *
     * @return the String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("%s%s %s",
                getStatusString(),
                getMarkString(),
                getName());
    }


    /**
     * Returns the String representation of the completion status of the task.
     *
     * @return the String representation of the completion status of the task.
     */
    private String getMarkString() {
        return (isComplete()) ? "[X]" : "[ ]";
    }


    /**
     * Returns the status of the task. If the task has ended, is ongoing or is
     * upcoming.
     *
     * @return the String representation of the status of the task.
     */
    private String getStatusString() {
        if (isEnded()) {
            return "[E]";
        } else if (isStarted()) {
            return "[O]";
        }
        return "[B]";
    }
}
