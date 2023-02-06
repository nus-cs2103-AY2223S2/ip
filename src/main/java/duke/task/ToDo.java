package duke.task;

import duke.exception.DescriptionException;

public class ToDo extends Task {
    /**
     * Constructor for a ToDo object based on the attributes that it has
     * @param description string representing the description
     * @throws DescriptionException If description is empty
     */
    public ToDo(String description) throws DescriptionException {
        super(description);
    }

    /**
     * String representation of the object
     * @return String representation of deadline
     */
    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
