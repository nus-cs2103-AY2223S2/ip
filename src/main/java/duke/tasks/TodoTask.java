package duke.tasks;

import duke.DukeException;

public class TodoTask extends Task {

    static final String INDICATOR = "[Todo]";

    public TodoTask(String name) throws DukeException {
        super(name);
    }

    @Override
    public String toString() {
        return INDICATOR + super.toString();
    }
}
