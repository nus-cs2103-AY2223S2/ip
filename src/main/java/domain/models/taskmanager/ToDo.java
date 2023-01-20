package domain.features.taskmanager.models;

import core.exceptions.InvalidArgumentException;

/**
 * A ToDo is just a very boring task.
 * Note that this class is not set to public by design. It is supposed to be
 * package private only. Therefore, anything that's related to this task
 * manager shall remain in this task manager.
 */
public class ToDo extends Task {
    /**
     * Creates a new todo.
     * @param name the name of the new todo.
     * @param isComplete whether if this todo is completed.
     */
    public ToDo(String name, boolean isComplete) {
        super(name, isComplete);
    }

    /**
     * Creates a todo that is completed.
     * @param name the name of the new todo.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Creates a new todo from the tokens.
     * @param tokens the tokens.
     * @return a new todo object.
     */
    public static ToDo fromTokens(String[] tokens) throws InvalidArgumentException {
        final String name = String.join(" ", tokens).trim();
        if (name.isBlank()) {
            throw new InvalidArgumentException("☹ OOPS, the name for a ToDo should" +
                    "not be blank.", tokens);
        }
        return new ToDo(String.join(" ", tokens));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
