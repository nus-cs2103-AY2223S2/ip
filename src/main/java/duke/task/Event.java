package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

import duke.DukeException;

/**
 * Class representing an event
 */
public class Event extends Task {
    protected static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d-M-yyyy[ HHmm]");
    protected static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma");
    protected String startStr;
    protected LocalDateTime startDateTime;
    protected String endStr;
    protected LocalDateTime endDateTime;

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

    /**
     * Returns an Event object, with tags.
     *
     * @param description Description of the event.
     * @param startStr A String representing the start date.
     * @param endStr A String representing the end date.
     * @param tags Tags of the Event.
     * @throws DukeException
     */
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
            TemporalAccessor temporalAccessor = INPUT_FORMATTER.parseBest(
                    startStr, LocalDateTime::from, LocalDate::from);
            if (temporalAccessor instanceof LocalDateTime) {
                startDateTime = (LocalDateTime) temporalAccessor;
            } else {
                startDateTime = ((LocalDate) temporalAccessor).atStartOfDay();
            }
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
            TemporalAccessor temporalAccessor = INPUT_FORMATTER.parseBest(
                    endStr, LocalDateTime::from, LocalDate::from);
            if (temporalAccessor instanceof LocalDateTime) {
                endDateTime = (LocalDateTime) temporalAccessor;
            } else {
                endDateTime = ((LocalDate) temporalAccessor).atStartOfDay();
            }
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
            startDateTime == null ? startStr : startDateTime.format(OUTPUT_FORMATTER),
            endDateTime == null ? endStr : endDateTime.format(OUTPUT_FORMATTER)
        );
    }
}
