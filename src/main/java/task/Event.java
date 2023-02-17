package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    String startString = null;
    String endString = null;
    LocalDate startDate;
    LocalDate endDate;

    /**
     * Constructor for a new Event object. Also processes the dates provided if written in the correct format.
     * @param details what the event is about
     * @param start when the event starts
     * @param end when the event ends
     */
    public Event(String details, String start, String end) {
        super(details);
        try {
            this.startDate = LocalDate.parse(start);
            this.startString = startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            this.endDate = LocalDate.parse(end);
            this.endString = endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException ignored) {
            if (startString == null) {
                this.startString = start;
                this.startDate = null;
            }
            if (endString == null) {
                this.endString = end;
                this.endDate = null;
            }
        }
    }

    @Override
    public String toString() {
        String task = super.toString();
        boolean isTaskEmpty = task.equals("");
        boolean isStartStringEmpty = startString.equals("");
        boolean isEndStringEmpty = endString.equals("");
        assert !isTaskEmpty && !isStartStringEmpty && !isEndStringEmpty: "Error checker did not catch missing input";
        return "[E] " + task + " (from: " + startString + " to: " + endString + ")";
    }

    /**
     * Returns the details of the deadline in a format that can be stored
     *  in the file for easy loading later.
     * @return the string to store in the file
     */
    @Override
    public String toStorageString() {
        return "E#" + super.toStorageString() + "#" + startString + "#" + endString;
    }
}
