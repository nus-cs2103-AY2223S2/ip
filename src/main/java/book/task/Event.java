package book.task;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String saveString() {
        return "E;" + this.isDone + ";" + this.description + ";"
                + startDateTime.format(Task.saveFormat) + ";"
                + endDateTime.format(Task.saveFormat);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startDateTime.format(Task.printFormat)
                + " to: " + endDateTime.format(Task.printFormat) + ")";
    }
}
