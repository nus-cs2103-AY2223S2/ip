package panav.task;

import panav.exception.ToDoDescriptionException;

/**
 * Class to represent an ToDo task. It contains attributes for it's description.
 */
public class ToDo extends Task {

    public static final String COMMAND_WORD = "todo";

    /**
     * Constructor to initialise attributes.
     * @param description tdo description.
     * @throws ToDoDescriptionException if there is no description.
     */
    public ToDo(String description) throws ToDoDescriptionException {
        super(description);
        if (description.length() == 0) {
            throw new ToDoDescriptionException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Returns command word of task.
     * @return command word.
     */
    @Override
    public String getCommand() {
        return ToDo.COMMAND_WORD;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
