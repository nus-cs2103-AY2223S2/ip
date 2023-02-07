package aqua.usertask;

import java.time.LocalDateTime;
import java.util.Optional;

import aqua.util.DateUtils;


/** A {@code UserTask} to represent a deadline. */
public class UserDeadline extends UserTask {
    /** Tag of {@code dueTime} parameter when parsing. */
    public static final String TAG_DUE_TIME = "by";

    private final boolean isComplete;
    private final LocalDateTime dueTime;


    /**
     * Constructs an {@code UserDeadline} from the given parameters while
     * setting the task completion to {@code false}.
     *
     * @param name - the name of the task.
     * @param dueTime - the time the task is due.
     */
    public UserDeadline(String name, LocalDateTime dueTime) {
        this(name, false, dueTime);
    }

    /**
     * Constructs an {@code UserDeadline} from the given parameters.
     *
     * @param name - the name of the task.
     * @param isComplete - if the task is completed.
     * @param dueTime - the time the task is due.
     */
    public UserDeadline(String name, boolean isComplete, LocalDateTime dueTime) {
        super(name);
        this.isComplete = isComplete;
        this.dueTime = dueTime;
    }


    @Override
    public UserDeadline mark(boolean isComplete) {
        return new UserDeadline(getName(), isComplete, dueTime);
    }


    @Override
    public boolean isComplete() {
        return isComplete;
    }


    @Override
    public Optional<LocalDateTime> getEnd() {
        return Optional.ofNullable(dueTime);
    }


    @Override
    public String getReloadString() {
        return String.format("deadline %s /%s %s /%s %s",
                getName(),
                TAG_DUE_TIME, dueTime,
                TAG_IS_COMPLETE, isComplete);
    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                DateUtils.formatNice(dueTime));
    }
}
