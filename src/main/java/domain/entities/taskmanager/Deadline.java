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
 * A Deadline is a Task with a deadline.
 * Note that this class is not set to public by design. It is supposed to be
 * package private only. Therefore, anything that's related to this task
 * manager shall remain in this task manager.
 */
public class Deadline extends Task {
    /**
<<<<<<< HEAD
     * Creates a new Deadline object from the tokens.
     * @param tokens The keywords after which we retrieve the important
     *               information.
     * @throws InvalidArgumentException for a deadline object to be valid,
     * its name must not be null, and it must have a deadline.
     */
    public Deadline(String[] tokens) throws InvalidArgumentException {
        super(tokens, delims);
        final Pair<String, Map<String, String>> tmp =
                Singletons.get(TokenUtilities.class).joinTokens(tokens, delims);
        if (tmp.getRight().get(deadlineKey) == null) {
            throw new InvalidArgumentException("☹ OOPS, did you forgot to " +
                    "type " + deadlineKey + " for your deadline?");
        }
        try {
            this.deadline =
                    LocalDate.parse(tmp.getRight().get(deadlineKey));
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
    private static final Set<String> delims = Set.of(deadlineKey,
            Task.completeKey);

    @Override
    public boolean containsDate(LocalDate date) {
        return deadline.equals(date);
    }

    @Override
    public String serialize() {
        return "deadline " + super.serialize() + " " + deadlineKey + " "
                + deadline;
    }

    @Override
    public String toString() {
        final DateTimeFormatter formatter = Singletons.get(DateTimeFormatter.class);
        return "[D]" + super.toString() + " (by: " + deadline.format(formatter)
                + ")";
    }
}
