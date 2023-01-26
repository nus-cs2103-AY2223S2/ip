package domain.entities.taskmanager;

import core.exceptions.InvalidArgumentException;
import core.singletons.Singletons;
import core.utils.TokenUtilities;

import java.util.HashSet;
import java.util.Set;

/**
 * A ToDo is just a very boring task.
 * Note that this class is not set to public by design. It is supposed to be
 * package private only. Therefore, anything that's related to this task
 * manager shall remain in this task manager.
 */
public class ToDo extends Task {
    public ToDo(String[] tokens) throws InvalidArgumentException {
        super(tokens, Set.of(Task.completeKey));
    }

    @Override
    public String serialize() {
        return "todo " + super.serialize();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
