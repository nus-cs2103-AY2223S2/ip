package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private String startFormatted;
    private String endFormatted;
    public Event(String description, String start, String end) {
        super(description);
        this.startFormatted = super.dateFormatter(start);
        this.endFormatted = super.dateFormatter(end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (Start: " + startFormatted + " | End: " + endFormatted + ")";
    }

    //event party /from 12/2/23 6:00PM /to 12/2/23 10:00PM
}
