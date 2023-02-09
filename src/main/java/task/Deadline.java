package task;

import util.DukeException;

/**
 * Represents a deadline task that the user wishes to
 * add to the list. A deadline object has a completion
 * status, description and time to complete by.
 */
public class Deadline extends Task {
    private String by;
    private String byFormatted;

    /**
     * Initialise a deadline task.
     * @param description
     * @param status
     * @param by
     * @throws DukeException
     */
    public Deadline(String description, boolean status, String by) throws DukeException {
        super(description, status);
        this.by = by;
        this.byFormatted = super.dateFormatter(this.by);
    }

    /**
     * {@inheritDoc}
     * @return Encoded task
     */
    public String serialise() {
        return String.format("Deadline,%s,%s,%s", super.getStatus(),
                super.getDescription(), this.by);
    }

    /**
     * Decodes a String representation of a task in the file
     * back into the Task object.
     *
     * @param data
     * @return Decoded Task object
     * @throws DukeException
     */
    public static Task deserialise(String data) throws DukeException {
        String[] arr = data.split(",");

        boolean isDone = Boolean.parseBoolean(arr[1]);
        String description = arr[2];
        String by = arr[3];

        return new Deadline(description, isDone, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byFormatted + ")";
    }

    //deadline test program /by 25/12/23 1150
}
