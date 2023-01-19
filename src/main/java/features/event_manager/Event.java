package features.event_manager;
import Exceptions.InvalidArgumentException;
import utils.Pair;
import utils.TokenUtilities;
import java.util.Map;
import java.util.Set;

/**
 * An Event is a Task with a starting time and a ending time.
 * Note that this class is not set to public by design. It is supposed to be
 * package private only. Therefore, anything that's related to this task
 * manager shall remain in this task manager.
 */
class Event extends Task {
    /**
     * Creates a new event.
     * @param name the name of the event
     * @param isComplete whether if the event is completed
     * @param startAt the starting time of the event
     * @param endAt the end time of the event
     */
    public Event(String name, boolean isComplete, String startAt, String endAt) {
        super(name, isComplete);
        this.startAt = startAt;
        this.endAt = endAt;
    }

    /**
     * Creates a new event that has not been completed.
     * @param name the name of the event.
     * @param startAt the starting time of the event.
     * @param endAt the end time of the event.
     */
    public Event(String name, String startAt, String endAt) {
        this(name, false, startAt, endAt);
    }

    public static Event fromTokens(String[] tokens) throws InvalidArgumentException {
        final Pair<String, Map<String, String>> tmp =
                TokenUtilities.instance.joinTokens(tokens, delims);
        if (tmp.getLeft().isBlank()) {
            throw new InvalidArgumentException("☹ OOPS, the name for an event " +
                    "should not be null", tokens);
        } else if (tmp.getRight().get(startAtKey) == null) {
            throw new InvalidArgumentException("☹ OOPS, did you forgot to type " +
                    startAtKey + " for your event?");
        } else if (tmp.getRight().get(endAtKey) == null) {
            throw new InvalidArgumentException("☹ OOPS, did you forgot to type " +
                    endAtKey + " for your event?");
        }
        return new Event(tmp.getLeft(), tmp.getRight().get(startAtKey),
                tmp.getRight().get(endAtKey));
    }
    private final String startAt;
    private final String endAt;

    /**
     * The token for getting the start time.
     */
    private static final String startAtKey = "/from";

    /**
     * The token for getting the end time.
     */
    private static final String endAtKey = "/to";

    /**
     * The set for retrieval of the values from the Token Utils
     */
    private static final Set<String> delims = Set.of(startAtKey, endAtKey);


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startAt + " to: " + endAt + ")";
    }
}
