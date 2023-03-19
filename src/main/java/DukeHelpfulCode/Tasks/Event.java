package DukeHelpfulCode.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    // tasks that start at a specific date/time and ends at a specific date/time

    LocalDateTime startDateTime;
    LocalDateTime endDateTime;

    public Event(String name, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(name,"event");
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
    public Event(String name, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean isDone){
        super(name, "event",isDone);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String toString() {
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return "[E] "
                + super.toString()
                + " (from: " + this.startDateTime.format(dtFormatter)
                + " to: " + this.endDateTime.format(dtFormatter) + ")";
    }

}
