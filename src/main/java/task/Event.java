package task;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String task, LocalDate startTime, LocalDate endTime) {
        super(task, false);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
       return String.format("[E]%s %s (from: %s to: %s )", super.formattedStatus(), super.task,
               super.formattedDate(this.startTime), super.formattedDate(this.endTime));
    }
}
