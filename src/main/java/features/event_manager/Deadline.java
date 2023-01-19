package features.event_manager;

import utils.Pair;
import utils.TokenUtilities;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

class Deadline extends Task {
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
    public static Deadline fromTokens(String[] tokens) {
        final Pair<String, Map<String, String>> res =
                TokenUtilities.instance.joinTokens(tokens, delims);
        return new Deadline(res.getLeft(), res.getRight().get(deadlineKey));
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
