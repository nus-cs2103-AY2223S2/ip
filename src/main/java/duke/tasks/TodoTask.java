package duke.tasks;

import duke.DukeException;

/**
 * Class representing a Todo task
 */
public class TodoTask extends Task {

    static final String INDICATOR = "[Todo]";

    /**
     * {@inheritDoc}
     */
    public TodoTask(String name) throws DukeException {
        super(name);
    }

    @Override
    public String toString() {
        return INDICATOR + super.toString();
    }
}
