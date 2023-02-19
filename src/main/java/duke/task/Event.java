package duke.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private String eventTime;
    private Date eventDate;

    Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    Event(String description, Date eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    @Override
    public String getOutputFormat() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, eventTime);
    }

    @Override
    public String toString() {
        if (eventDate != null) {
            DateFormat dateEventFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return String.format("[E]%s (at: %s)", super.toString(), dateEventFormat.format(eventDate));
        } else {
            return String.format("[E]%s (at: %s)", super.toString(), eventTime);
        }
    }
}