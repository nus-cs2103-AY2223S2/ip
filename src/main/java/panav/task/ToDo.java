package panav.task;

import panav.exception.ToDoDescriptionException;

/**
 * Class to represent an ToDo task. It contains attributes for it's description.
 */
public class ToDo extends Task {

    /**
     * Constructor to initialise attributes.
     * @param description
     * @throws ToDoDescriptionException
     */
    public ToDo(String description) throws ToDoDescriptionException {
        super(description);
        if (description.length() == 0) {
            throw new ToDoDescriptionException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
