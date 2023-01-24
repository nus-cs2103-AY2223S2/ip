package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String startTime;
    protected LocalDateTime startObj;
    protected String endTime;
    protected LocalDateTime endObj;
    protected static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    protected static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma");
    public Event(String description, String startTime, String endTime) throws DukeException {
        super(description.trim());
        this.startTime = startTime.trim();
        this.endTime = endTime.trim();
        if (this.description.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        if (this.startTime.equals("") || this.endTime.equals("")) {
            throw new DukeException("The start and/or end time of a deadline cannot be empty.");
        }
        try {
            startObj = LocalDateTime.parse(startTime, inputFormatter);
        } catch (DateTimeParseException e) {
            startObj = null;
        }
        try {
            endObj = LocalDateTime.parse(endTime, inputFormatter);
        } catch (DateTimeParseException e) {
            endObj = null;
        }
    }
    /**
     * Represent duke.Event as a string
     * @return String representation of a duke.Event
     */
    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(),
                startObj == null ? startTime : startObj.format(outputFormatter),
                endObj == null ? endTime : endObj.format(outputFormatter)
        );
    }
}
