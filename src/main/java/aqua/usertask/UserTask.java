package aqua.usertask;

import java.time.LocalDateTime;
import java.util.Optional;

import aqua.storage.Reloadable;


/** Represents a user created task. */
public abstract class UserTask implements Reloadable {
    /** Tag of {@code isCompleted} argument when parsing. */
    public static final String TAG_IS_COMPLETE = "completed";

    private final String name;


    /**
     * Constructs a {@code UserTask} with the specified name.
     *
     * @param name - the name of the task.
     */
    public UserTask(String name) {
        this.name = name;
    }


    /**
     * Constructs a {@code UserTask} that is a copy of the given task.
     *
     * @param task - the reference task.
     */
    public UserTask(UserTask task) {
        this(task.name);
    }


    /**
     * Sets the task's completion status as specified.
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
     * Returns the ending time of the task wrapped in an {@code Optional}. If
     * the task has no ending time, {@code Optional.empty} is returned.
     *
     * @return the ending time of the task wrapped in an {@code Optional}.
     */
    public Optional<LocalDateTime> getEnd() {
        return Optional.empty();
    }


    @Override
    public String toString() {
        return String.format("%s %s",
                isComplete() ? "[X]" : "[ ]",
                name);
    }
}
