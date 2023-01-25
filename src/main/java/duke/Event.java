package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String startStr;
    protected LocalDateTime startDateTime;
    protected String endStr;
    protected LocalDateTime endDateTime;
    protected static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    protected static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma");
    public Event(String description, String startStr, String endStr) throws DukeException {
        super(description.trim(), Icon.EVENT);
        this.startStr = startStr.trim();
        this.endStr = endStr.trim();
        if (this.description.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        if (this.startStr.equals("") || this.endStr.equals("")) {
            throw new DukeException("The start and/or end time of a deadline cannot be empty.");
        }
        try {
            startDateTime = LocalDateTime.parse(startStr, inputFormatter);
        } catch (DateTimeParseException e) {
            startDateTime = null;
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
