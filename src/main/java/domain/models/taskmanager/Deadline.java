package domain.models.taskmanager;
import core.exceptions.InvalidArgumentException;
import core.injections.Injections;
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
     * Creates a new deadline object whose isComplete is set to false.
     * @param name the name of the deadline object.
     * @param deadline the deadline of the deadline object.
     * @param isComplete if the deadline object is complete or not.
     */
    public Deadline(String name, boolean isComplete, String deadline) {
        super(name, isComplete);
        this.deadline = deadline;
    }

    /**
     * Creates a new deadline object whose isComplete is set to false.
     * @param name the name of the deadline object.
     * @param deadline the deadline of the deadline object.
     */
    public Deadline(String name, String deadline) {
        this(name, false, deadline);
    }

    /**
     * Creates a new deadline from the given tokens.
     * @param tokens the tokens.
     * @return a new deadline from the given tokens.
     */
    public static Deadline fromTokens(String[] tokens) throws InvalidArgumentException {
        final Pair<String, Map<String, String>> tmp =
                Injections.get(TokenUtilities.class).joinTokens(tokens, delims);
        if (tmp.getLeft().isBlank()) {
            throw new InvalidArgumentException("☹ OOPS, the name for a deadline " +
                    "should not be null", tokens);
        } else if (tmp.getRight().get(deadlineKey) == null) {
            throw new InvalidArgumentException("☹ OOPS, did you forgot to type " +
                    deadlineKey + " for your deadline?");
        }
        return new Deadline(tmp.getLeft(), tmp.getRight().get(deadlineKey));
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
    private static final Set<String> delims = Set.of(deadlineKey);

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
