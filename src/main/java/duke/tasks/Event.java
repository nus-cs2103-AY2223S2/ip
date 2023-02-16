package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exception.DukeException;

/**
 * Represents Task object with event type.
 */
public class Event extends Task {

    private final LocalDate start;
    private final LocalDate end;

    /**
     * Creates a new Deadline object with parent constructor and date
     *
     *  @param description Task description of the Event object
     * @param start Starting datetime of the event
     * @param end Ending datetime of the event
     * @throws DukeException if there is error parsing datetime
     */
    public Event(String description, String start, String end) throws DukeException {
        super(description);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new DukeException("Parse Error: " + e.getMessage() + "\n"
                    + "Accepted format: \"YYYY-MM-DD\"");
        }

    }

    /**
     * Generates a data string representation of the Event object. It will be used
     * to store the Event object in storage file.
     *
     * @return Data string of the task
     */
    @Override
    public String getData() {
        StringBuilder sb = new StringBuilder();
        sb.append("E | ");
        if (isTaskDone()) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }
        sb.append(this.description).append(" | ");
        sb.append(this.start).append(" | ").append(this.end).append("\n");
        ;
        return sb.toString();
    }

    /**
     * Returns string representation of the event task
     *
     * @return String representation of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.getMonth().toString().substring(0, 3) + " "
                + start.getDayOfMonth() + " " + start.getYear()
                + " to: " + end.getMonth().toString().substring(0, 3) + " "
                + end.getDayOfMonth() + " " + end.getYear() + ")";
    }
}
