package duke.tasks;

import duke.DukeException;

/**
 * Class representing a Todo task
 */
public class TodoTask extends Task {

    static final String INDICATOR = "[Todo]";

    /**
     * Constructor for a Todo task
     *
     * @param name Name of Todo task
     * @throws DukeException If name is empty
     */
    public TodoTask(String name) throws DukeException {
        super(name);
    }

    @Override
    public String toString() {
        return INDICATOR + super.toString();
    }
}
