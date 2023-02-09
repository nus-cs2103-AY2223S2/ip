package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String startStr;
    protected LocalDateTime startDateTime;
    protected String endStr;
    protected LocalDateTime endDateTime;
    protected static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
    protected static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma");

    /**
     * Returns an Event object.
     *
     * @param description Description of the event.
     * @param startStr A String representing the start date.
     * @param endStr A String representing the end date.
     * @throws DukeException If any of the given arguments are empty.
     */
    public Event(String description, String startStr, String endStr) throws DukeException {
        super(description.trim(), TaskIcon.EVENT);
        setStartTime(startStr);
        setEndTime(endStr);
    }

    public Event(String description, String startStr, String endStr, String tags) throws DukeException {
        super(description.trim(), TaskIcon.EVENT, tags);
        setStartTime(startStr);
        setEndTime(endStr);
    }

    protected void setStartTime(String startStr) throws DukeException {
        this.startStr = startStr.trim();
        if (this.startStr.equals("")) {
            throw new DukeException("The start time of an event cannot be empty.");
        }
        try {
            startDateTime = LocalDateTime.parse(startStr, inputFormatter);
        } catch (DateTimeParseException e) {
            startDateTime = null;
        }
    }

    protected void setEndTime(String endStr) throws DukeException {
        this.endStr = endStr.trim();
        if (this.endStr.equals("")) {
            throw new DukeException("The end time of an event cannot be empty.");
        }
        try {
            endDateTime = LocalDateTime.parse(endStr, inputFormatter);
        } catch (DateTimeParseException e) {
            endDateTime = null;
        }
    }

    public String getStartTime() {
        return startStr;
    }

    public String getEndTime() {
        return endStr;
    }

    @Override
    public String toString() {
        return String.format(
            "%s (from: %s to: %s)",
            super.toString(),
            startDateTime == null ? startStr : startDateTime.format(outputFormatter),
            endDateTime == null ? endStr : endDateTime.format(outputFormatter)
        );
    }
}
