package duke.tasks;

import duke.DukeException;
import duke.UserInterface;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private static final String COMPLETED = " [O] ";
    private static final String NOT_COMPLETED = " [ ] ";
    static final String EXTRAS_FORMAT_TEMPLATE = " (%s)";
    private static final String FORMAT_EXCEPTION = "Invalid format for creating Task";
    private static final String MARKED_RESPONSE = "Well done. Task has been marked as completed:\n";
    private static final String UNMARKED_RESPONSE = "Got it. Task has been unmarked:\n";
    private final String name;
    private boolean isCompleted;
    Task(String name) throws DukeException {
        if (name.isEmpty()) {
            throw new DukeException(FORMAT_EXCEPTION);
        }
        this.name = name;
        isCompleted = false;
    }

    public String mark() {
        isCompleted = true;
        return MARKED_RESPONSE + UserInterface.INDENT + this;
    }

    public String unmark() {
        isCompleted = false;
        return UNMARKED_RESPONSE + UserInterface.INDENT + this;
    }

    @Override
    public String toString() {
        return (isCompleted ? COMPLETED : NOT_COMPLETED) + name;
    }
}
