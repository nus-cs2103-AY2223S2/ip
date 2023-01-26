package domain.entities.taskmanager;

import core.exceptions.InvalidArgumentException;
import core.singletons.Singletons;
import core.utils.Pair;
import core.utils.TokenUtilities;

import java.util.Map;
import java.util.Set;

/**
 * An Event is a Task with a starting time and a ending time.
 * Note that this class is not set to public by design. It is supposed to be
 * package private only. Therefore, anything that's related to this task
 * manager shall remain in this task manager.
 */
public class Event extends Task {
    /**
     * Creates a new Event object from the tokens.
     * @param tokens The keywords after which we retrieve the important
     *               information.
     * @throws InvalidArgumentException for an event object to be valid,
     * its name must not be null, and it must have a starting time and an
     * ending time.
     */
    public Event(String[] tokens) throws InvalidArgumentException {
        super(tokens, delims);
        final Pair<String, Map<String, String>> tmp =
                Singletons.get(TokenUtilities.class).joinTokens(tokens, delims);
        if (tmp.getLeft().isBlank()) {
            throw new InvalidArgumentException("☹ OOPS, the name for an " +
                    "event " + "should not be null", tokens);
        } else if (tmp.getRight().get(startAtKey) == null) {
            throw new InvalidArgumentException("☹ OOPS, did you forgot to " +
                    "type " + startAtKey + " for your event?");
        } else if (tmp.getRight().get(endAtKey) == null) {
            throw new InvalidArgumentException("☹ OOPS, did you forgot to " +
                    "type " + endAtKey + " for your event?");
        }
        this.startAt = tmp.getRight().get(startAtKey);
        this.endAt = tmp.getRight().get(endAtKey);
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
    private static final Set<String> delims = Set.of(startAtKey, endAtKey,
            Task.completeKey);

    @Override
    public String serialize() {
        return "event " + super.serialize() + " " + startAtKey + " " +
                startAt + " " + endAtKey + " " + endAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startAt + " to: " + endAt + ")";
    }
}
