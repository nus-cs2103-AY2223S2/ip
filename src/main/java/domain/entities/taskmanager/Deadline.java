package domain.entities.taskmanager;

import core.exceptions.InvalidArgumentException;
import core.singletons.Singletons;
import core.utils.Pair;
import core.utils.TokenUtilities;

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
        if (tmp.getLeft().isBlank()) {
            throw new InvalidArgumentException("☹ OOPS, the name for a " +
                    "deadline " + "should not be null", tokens);
        } else if (tmp.getRight().get(deadlineKey) == null) {
            throw new InvalidArgumentException("☹ OOPS, did you forgot to " +
                    "type " + deadlineKey + " for your deadline?");
        }
        this.deadline = tmp.getRight().get(deadlineKey);
    }

    /**
     * The deadline.
     */
    private final String deadline;

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
    public String serialize() {
        return "deadline " + super.serialize() + " " + deadlineKey + " "
                + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
