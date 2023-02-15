package task;

import util.DukeException;

/**
 * Represents an event task that the user wishes to add
 * to the list. An event object has a completion status,
 * description, start and end timings.
 */
public class Event extends Task {
    private String startFormatted;
    private String start;
    private String endFormatted;
    private String end;

    /**
     * Initialise an event task.
     * <p>
     * @param description
     * @param status
     * @param start
     * @param end
     * @throws DukeException
     */
    public Event(String description, boolean status, String start, String end) throws DukeException {
        super(description, status);
        this.start = start;
        this.end = end;
        assert super.dateFormatter(start) != null;
        this.startFormatted = super.dateFormatter(start);
        assert super.dateFormatter(end) != null;
        this.endFormatted = super.dateFormatter(end);
    }

    /**
     * {@inheritDoc}
     * @return Encoded task
     */
    @Override
    public String serialise() {
        return String.format("Event,%s,%s,%s,%s", super.getStatus(),
                super.getDescription(), this.start, this.end);
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
        assert arr.length != 0;

        boolean isDone = Boolean.parseBoolean(arr[1]);
        String description = arr[2];
        String start = arr[3];
        String end = arr[4];

        return new Event(description, isDone, start, end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (Start: " + startFormatted + " | End: " + endFormatted + ")";
    }
}
