package twofive.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String taskDescription, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskDescription);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String eventString = " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy hh:mma"))
                + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy hh:mma")) + ")";
        return "[E]" + super.toString() + eventString;
    }

    @Override
    public String getFileWriteString() {
        return "E" + super.getFileWriteString() + " | "
                + this.startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " | "
                + this.endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public boolean isToday(LocalDate date) {
        return this.startTime.toLocalDate().isEqual(date) || this.endTime.toLocalDate().isEqual(date);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Event)) {
            return false;
        }

        Event event = (Event) o;

        return super.equals(event) && event.startTime.equals(this.startTime) && event.endTime.equals(this.endTime);
    }
}
