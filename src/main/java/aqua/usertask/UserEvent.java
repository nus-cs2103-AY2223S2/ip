package aqua.usertask;

import java.time.LocalDateTime;
import java.util.Optional;

import aqua.util.DateUtils;


/** A {@code UserTask} to represent an event */
public class UserEvent extends UserTask {
    /** Tag of {@code startTime} argument when parsing. */
    public static final String TAG_START_TIME = "from";
    /** Tag of {@code endTime} argument when parsing. */
    public static final String TAG_END_TIME = "to";

    private final boolean isComplete;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;


    /**
     * Constructs a {@code UserEvent} from the given parameters while setting
     * the event's completion status to {@code false}.
     *
     * @param name - the name of the event.
     * @param startTime - the time the event starts.
     * @param endTime - the time the event ends.
     */
    public UserEvent(String name, LocalDateTime startTime, LocalDateTime endTime) {
        this(name, false, startTime, endTime);
    }

    /**
     * Constructs a {@code UserEvent} from the given parameters.
     *
     * @param name - the name of the event.
     * @param isCompelte - if the event is completed.
     * @param startTime - the time the event starts.
     * @param endTime - the time the event ends.
     */
    public UserEvent(String name, boolean isComplete, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.isComplete = isComplete;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    @Override
    public UserEvent mark(boolean isComplete) {
        return new UserEvent(getName(), isComplete, startTime, endTime);
    }


    @Override
    public boolean isComplete() {
        return isComplete;
    }


    @Override
    public Optional<LocalDateTime> getStart() {
        return Optional.ofNullable(startTime);
    }


    @Override
    public Optional<LocalDateTime> getEnd() {
        return Optional.ofNullable(endTime);
    }


    @Override
    public String getReloadString() {
        return String.format("event %s /%s %s /%s %s /%s %s",
                getName(),
                TAG_START_TIME, startTime,
                TAG_END_TIME, endTime,
                TAG_IS_COMPLETE, isComplete);
    }


    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                DateUtils.formatNice(startTime),
                DateUtils.formatNice(endTime));
    }
}
