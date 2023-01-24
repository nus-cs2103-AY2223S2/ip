package aqua.aquatask;

import java.time.LocalDateTime;
import java.util.Optional;

import aqua.util.DateUtils;

/** An implementation of AquaTask to represent a deadline. */
public class AquaDeadline extends AquaTask {
    /** Tag of {@code by} argument when parsing. */
    public static final String BY_TAG = "by";

    /** If the task is completed. */
    private final boolean isComplete;
    /** Time the task is due. */
    private final LocalDateTime by;


    /**
     * Constructs an AquaDeadline from the given parameters while setting the
     * task completion to {@code false}.
     * 
     * @param name - the name of the task.
     * @param by - the time the task is due.
     */
    public AquaDeadline(String name, LocalDateTime by) {
        this(name, false, by);
    }

    /**
     * Constructs an AquaDeadline from the given parameters.
     * 
     * @param name - the name of the task.
     * @param isComplete - if the task is completed.
     * @param by - the time the task is due.
     */
    public AquaDeadline(String name, boolean isComplete, LocalDateTime by) {
        super(name);
        this.isComplete = isComplete;
        this.by = by;
    }


    @Override
    public AquaDeadline mark(boolean isComplete) {
        return new AquaDeadline(this.getName(), isComplete, this.by);
    }


    @Override
    public boolean isComplete() {
        return this.isComplete;
    }


    @Override
    public Optional<LocalDateTime> getEnd() {
        return Optional.ofNullable(by);
    }
    
    
    @Override
    public String getReloadString() {
        return String.format(
            "deadline %s /%s %s /%s %s",
            getName(),
            BY_TAG, by,
            IS_COMPLETED_TAG, isComplete
        );
    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), DateUtils.formatNice(by));
    }
}
