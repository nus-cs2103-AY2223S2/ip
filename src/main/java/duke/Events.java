package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected String startTime;
    protected String endTime;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Events(String description, String  start, String  end) throws DukeInvalidArgumentException {
        super(description);
        try {
            this.start = LocalDateTime.parse(start);
            this.end = LocalDateTime.parse(end);
            this.startTime = start;
            this.endTime = end;
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException("The format of date-time is invalid.");
        }
    }

    public String getStart() {
        return startTime;
    }

    public String getEnd() {
        return endTime;
    }

    public void getDeadline() {
        System.out.println("The event is from" + start.format(format) + " to "
                + end.format(format));
    }

    public String toString() {
        return "[E]" + super.toString() + "(from: " + start.format(format) + " to: "
                + end.format(format) + ")";
    }
}
