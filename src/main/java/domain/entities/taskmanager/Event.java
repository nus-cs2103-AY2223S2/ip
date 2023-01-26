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
 */
public class Event extends Task {
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
    private final LocalDate startAt;
    private final LocalDate endAt;

    /**
     * Creates a new Event object from the tokens.
     *
     * @param tokens The keywords after which we retrieve the important
     *               information.
     * @throws InvalidArgumentException for an event object to be valid,
     *                                  its name must not be null, and it must
     *                                  have a starting time and an ending time.
     */
    public Event(String[] tokens) throws InvalidArgumentException {
        super(tokens, delims);
        final Pair<String, Map<String, String>> tmp =
                Singletons.get(TokenUtilities.class).joinTokens(tokens, delims);
        if (tmp.getLeft().isBlank()) {
            throw new InvalidArgumentException("☹ OOPS, the name for an "
                    + "event " + "should not be null", tokens);
        } else if (tmp.getRight().get(startAtKey) == null) {
            throw new InvalidArgumentException("☹ OOPS, did you forgot to "
                    + "type " + startAtKey + " for your event?");
        } else if (tmp.getRight().get(endAtKey) == null) {
            throw new InvalidArgumentException("☹ OOPS, did you forgot to "
                    + "type " + endAtKey + " for your event?");
        }
        try {
            startAt = LocalDate.parse(tmp.getRight().get(startAtKey));
            endAt = LocalDate.parse(tmp.getRight().get(endAtKey));
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("☹ OOPS, the date format for "
                    + "your event is wrong. Please use yyyy-mm-dd", tokens);
        }
    }

    @Override
    public boolean containsDate(LocalDate date) {
        return date.isAfter(startAt) && date.isBefore(endAt);
    }

    @Override
    public String serialize() {
        return "event " + super.serialize() + " " + startAtKey + " "
                + startAt + " " + endAtKey + " " + endAt;
    }

    @Override
    public String toString() {
        final DateTimeFormatter formatter =
                Singletons.get(DateTimeFormatter.class);
        return "[E]" + super.toString() + " (from: " + startAt.format(formatter)
                + " to: " + endAt.format(formatter) + ")";
    }
}
