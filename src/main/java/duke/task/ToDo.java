package duke.task;

import duke.TaskCreationException;

/**
 * To-do class
 */
public class ToDo extends Task {
    public ToDo(String desc) throws TaskCreationException {
        super(desc);
    }

    @Override
    protected String getType() {
        return "[T]";
    }

}
