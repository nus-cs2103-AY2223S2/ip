package task;

import util.DukeException;

/**
 * Represents a todo task that the user wishes to add
 * to the list. A todo object has a completion status
 * and description.
 */
public class ToDo extends Task {

    /**
     * Initialise a todo task.
     * <p>
     * @param description
     * @param status
     */
    public ToDo(String description, boolean status) {
        super(description, status);
    }
    /**
     * {@inheritDoc}
     * @return Encoded task
     */
    @Override
    public String serialise() {
        return String.format("Todo,%s,%s", super.getStatus(), super.getDescription());
    }

    /**
     * Decodes a String representation of a task in the file
     * back into the Task object.
     *
     * @param data
     * @return Decoded Task object
     * @throws DukeException
     */
    public static Task deserialise(String data) {
        String[] arr = data.split(",");
        assert arr.length != 0;

        boolean isDone = Boolean.parseBoolean(arr[1]);
        String description = arr[2];

        return new ToDo(description, isDone);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
