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
    public Deadline(String description, boolean status, String by) throws DukeException {
        super(description, status);
        this.by = by;
        assert super.dateFormatter(this.by) != null;
        this.byFormatted = super.dateFormatter(this.by);
    }

    public String serialise() {
        assert super.getStatus() != null;
        return String.format("Deadline,%s,%s,%s", super.getStatus(),
                super.getDescription(), this.by);
    }
    public static Task deserialise(String data) throws DukeException {
        String[] arr = data.split(",");
        assert arr.length != 0;
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
