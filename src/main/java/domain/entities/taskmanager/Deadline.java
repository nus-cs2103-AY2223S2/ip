package domain.entities.taskmanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Set;

import core.exceptions.InvalidArgumentException;
import core.singletons.Singletons;
import core.utils.Pair;
import core.utils.TokenUtilities;

/**
 * A Deadline is a Task with a deadline.
 */
public class Deadline extends Task {
    /**
     * The key used for retrieving the deadline.
     */
    private static final String deadlineKey = "/by";
    /**
     * The set of keys for retrieving the data.
     */
    private static final Set<String> delims = Set.of(deadlineKey,
            Task.COMPLETE_KEY);
    /**
     * The deadline.
     */
    private final LocalDate deadline;

    /**
     * <<<<<<< HEAD
     * Creates a new Deadline object from the tokens.
     *
     * @param tokens The keywords after which we retrieve the important
     *               information.
     * @throws InvalidArgumentException for a deadline object to be valid,
     *                                  its name must not be null, and it must have a deadline.
     */
    public Deadline(String[] tokens) throws InvalidArgumentException {
        super(tokens, delims);
        final Pair<String, Map<String, String>> tmp =
                Singletons.get(TokenUtilities.class).joinTokens(tokens, delims);
        if (tmp.getRight().get(deadlineKey) == null) {
            throw new InvalidArgumentException("☹ OOPS, did you forgot to "
                    + "type " + deadlineKey + " for your deadline?");
        }
        try {
            this.deadline =
                    LocalDate.parse(tmp.getRight().get(deadlineKey));
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("☹ OOPS, the deadline you typed "
                    + "is not in the correct format. Please type it in the "
                    + "format of yyyy-mm-dd");
        }
    }

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

    @Override
    public int compareTo(Task o) {
        final int res = super.compareTo(o);
        if (res != 0) {
            return res;
        }
        if (o instanceof Event) {
            return 1;
        } else if (o instanceof ToDo) {
            return -1;
        }
        final Deadline ddl = (Deadline) o;
        if (this.deadline.isBefore(ddl.deadline)) {
            return -1;
        } else if (this.deadline.isEqual(ddl.deadline)) {
            return this.name.compareTo(ddl.name);
        } else {
            return 1;
        }
    }
}
