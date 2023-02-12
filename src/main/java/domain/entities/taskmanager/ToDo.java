package domain.entities.taskmanager;

import java.util.Set;

import core.exceptions.InvalidArgumentException;

/**
 * A {@link ToDo} is just a very boring task.
 */
public class ToDo extends Task {
    /**
     * Creates a new {@link ToDo} object from the tokens.
     *
     * @param tokens The keywords after which we retrieve the important
     *               information.
     * @throws InvalidArgumentException for a {@link ToDo} object to be
     *                                  valid, it has to have a name.
     */
    public ToDo(String[] tokens) throws InvalidArgumentException {
        super(tokens, Set.of(Task.COMPLETE_KEY));
    }

    @Override
    public String serialize() {
        return "todo " + super.serialize();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public int compareTo(Task o) {
        final int res = super.compareTo(o);
        if (res != 0) {
            return res;
        }
        // Todos should be placed last
        if (!(o instanceof ToDo)) {
            return 1;
        }
        // for todos, we sort them by their name.
        return this.name.compareTo(o.name);
    }
}
