import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Contains information of an event
 * Contains description and duration of the event
 */
public class Event extends Task {

    private static final String TASK_TYPE = "E";
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates an event object
     *
     * @param description The description of the event
     * @param from Starting time of the event
     * @param to Ending time of the event
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        DukeException.ErrorType errType = DukeException.ErrorType.TIME;
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
            if (LocalDate.now().isAfter(this.to)) {
                throw new DukeException(errType, "Event Ended");
            } else if (this.to.isBefore(this.from)) {
                throw new DukeException(errType, "Invalid Event Duration");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException(errType, "DateTime Parse Exception");
        }
    }

    /**
     * Creates a deadline object
     *
     * @param description Description of the deadline task
     * @param isDone Completion status of the deadline task
     * @param from Starting time of the event
     * @param to Ending time of the event
     */
    public Event(String description, boolean isDone, String from, String to) throws DukeException {
        super(description, isDone);
        DukeException.ErrorType errType = DukeException.ErrorType.TIME;
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
            if (LocalDate.now().isAfter(this.to)) {
                throw new DukeException(errType, "Event Ended");
            } else if (this.to.isBefore(this.from)) {
                throw new DukeException(errType, "Invalid Event Duration");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException(errType, "DateTime Parse Exception");
        }
    }

    /**
     * Creates a deadline object from user input
     * Handles exceptions
     *
     * @param input Input from user
     * @return Deadline Task object
     * @throws DukeException If description of the event is empty
     * @throws DukeException If start time of the event is empty
     * @throws DukeException If end time of the event is empty
     */
    public static Event generate(String input) throws DukeException {
        DukeException.ErrorType errType = DukeException.ErrorType.EVENT;

        // Cleans input command
        input = input.trim();

        // Checks format of input command
        int indexDesc = input.indexOf(" ");
        int indexFrom = input.indexOf(" /from ");
        int indexTime = input.indexOf(" /to ");
        if (indexDesc < 0) {
            throw new DukeException(errType, "Empty description");
        } else if (indexFrom < 0) {
            throw new DukeException(errType, "Empty From Time");
        } else if (indexTime < 0) {
            throw new DukeException(errType, "Empty To Time");
        }

        // Cleans and checks variables
        String description = input.substring(indexDesc + 1, indexFrom).trim();
        String timeFrom = input.substring(indexFrom + 7, indexTime).trim();
        String timeTo = input.substring(indexTime + 5).trim();
        if (description.equals("")) {
            throw new DukeException(errType, "Empty description");
        } else if (timeFrom.equals("")) {
            throw new DukeException(errType, "Empty From Time");
        } else if (timeTo.equals("")) {
            throw new DukeException(errType, "Empty To Time");
        }

        return new Event(description, timeFrom, timeTo);
    }

    /**
     * Returns deadline task from save string
     *
     * @param details details of the deadline task
     * @param status Completion status of the deadline task
     * @return Task in save string format
     */
    public static Event load(String details, String status, String divider) throws DukeException {
        try {
            boolean isDone = status.equals("X");
            String[] data = details.split(divider, 2);
            String description = data[0];
            String[] duration = data[1].split(" - ", 2);
            return new Event(description, isDone, duration[0], duration[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(DukeException.ErrorType.FILE, "Incorrect Save Format");
        }
    }

    /**
     * Returns the start time to end time of event
     *
     * @return Duration of event
     */
    public String getDuration() {
        return from + " - " + to;
    }

    /**
     * Returns type of task, completion status, description, and duration of
     * the event
     *
     * @return Type of task, completion status, description, and duration of
     * the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from
                        .format(DateTimeFormatter
                                .ofPattern("MMM d yyyy"))
                + " to: " + to
                        .format(DateTimeFormatter
                                .ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Returns event task in save string format
     *
     * @param divider Divider used to separate fields
     * @return Task in save string format
     */
    @Override
    public String toSave(String divider) {
        String duration = getDuration();
        return TASK_TYPE
                + divider + getStatusIcon()
                + divider + description
                + divider + duration;
    }
}
