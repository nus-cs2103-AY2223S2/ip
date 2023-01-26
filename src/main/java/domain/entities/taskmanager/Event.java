package domain.entities.taskmanager;
import core.exceptions.InvalidArgumentException;
import core.singletons.Singletons;
import core.utils.Pair;
import core.utils.TokenUtilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
     * Creates a new event.
     * @param name the name of the event
     * @param isComplete whether if the event is completed
     * @param startAt the starting time of the event
     * @param endAt the end time of the event
     */
    public Event(String name, boolean isComplete, LocalDate startAt, LocalDate endAt) {
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
    public Event(String name, LocalDate startAt, LocalDate endAt) {
        this(name, false, startAt, endAt);
    }

    public static Event fromTokens(String[] tokens) throws InvalidArgumentException {
        final Pair<String, Map<String, String>> tmp =
                Singletons.get(TokenUtilities.class).joinTokens(tokens, delims);
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
        try {
            return new Event(tmp.getLeft(),
                    LocalDate.parse(tmp.getRight().get(startAtKey)),
                    LocalDate.parse(tmp.getRight().get(endAtKey))
            );
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("☹ OOPS, the date format for " +
                    "your event is wrong. " +
                    "Please use yyyy-mm-dd", tokens);
        }
    }
    private final LocalDate startAt;
    private final LocalDate endAt;

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
        final DateTimeFormatter formatter =
                Singletons.get(DateTimeFormatter.class);
        return "[E]" + super.toString() + " (from: " + startAt.format(formatter) +
                " to: " + endAt.format(formatter) + ")";
    }
}
