package domain.entities.taskmanager;

import core.exceptions.InvalidArgumentException;
import core.singletons.Singletons;
import core.utils.Pair;
import core.utils.TokenUtilities;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Set;

/**
 * A Deadline is a Task with a deadline.
 * Note that this class is not set to public by design. It is supposed to be
 * package private only. Therefore, anything that's related to this task
 * manager shall remain in this task manager.
 */
public class Deadline extends Task {
    /**
     * Creates a new deadline object whose isComplete is set to false.
     *
     * @param name       the name of the deadline object.
     * @param deadline   the deadline of the deadline object.
     * @param isComplete if the deadline object is complete or not.
     */
    public Deadline(String name, boolean isComplete, LocalDate deadline) {
        super(name, isComplete);
        this.deadline = deadline;
    }

    /**
     * Creates a new deadline object whose isComplete is set to false.
     *
     * @param name     the name of the deadline object.
     * @param deadline the deadline of the deadline object.
     */
    public Deadline(String name, LocalDate deadline) {
        this(name, false, deadline);
    }

    /**
     * Creates a new deadline from the given tokens.
     *
     * @param tokens the tokens.
     * @return a new deadline from the given tokens.
     */
    public static Deadline fromTokens(String[] tokens) throws InvalidArgumentException {
        final Pair<String, Map<String, String>> tmp =
                Singletons.get(TokenUtilities.class).joinTokens(tokens, delims);
        if (tmp.getLeft().isBlank()) {
            throw new InvalidArgumentException("☹ OOPS, the name for a deadline " +
                    "should not be null", tokens);
        } else if (tmp.getRight().get(deadlineKey) == null) {
            throw new InvalidArgumentException("☹ OOPS, did you forgot to type " +
                    deadlineKey + " for your deadline?");
        }
        try {
            final LocalDate deadline =
                    LocalDate.parse(tmp.getRight().get(deadlineKey));
            return new Deadline(tmp.getLeft(), deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("☹ OOPS, the deadline you typed is " +
                    "not in the correct format. Please type it in the format of yyyy-mm-dd");
        }
    }

    /**
     * The deadline.
     */
    private final LocalDate deadline;

    /**
     * The key used for retrieving the deadline.
     */
    private static final String deadlineKey = "/by";

    /**
     * The set of keys for retrieving the data.
     */
    private static final Set<String> delims = Set.of(deadlineKey);

    @Override
    public String toString() {
        final DateTimeFormatter formatter = Singletons.get(DateTimeFormatter.class);
        return "[D]" + super.toString() + " (by: " + deadline.format(formatter)
                + ")";
    }
}
