package duke.tasks;

import java.io.Serializable;

import duke.DukeException;
import duke.UserInterface;


/**
 * Abstract parent class of task classes
 */
public abstract class Task implements Serializable {
    static final String EXTRAS_FORMAT_TEMPLATE = " (%s)";
    private static final String COMPLETED = " [O] ";
    private static final String NOT_COMPLETED = " [ ] ";
    private static final String FORMAT_EXCEPTION = "Invalid format for creating Task";
    private static final String MARKED_RESPONSE = "Well done. Task has been marked as completed:\n";
    private static final String UNMARKED_RESPONSE = "Got it. Task has been unmarked:\n";

    private final String name;
    private boolean isCompleted;

    /**
     * Constructor for a task
     * @param name Given task name
     * @throws DukeException if no name is provided
     */
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
        String completionStatus = isCompleted ? COMPLETED : NOT_COMPLETED;
        return completionStatus + name;
    }
}
