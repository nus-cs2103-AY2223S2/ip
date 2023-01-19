package task;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String task, LocalDate startTime, LocalDate endTime) {
        super(task, false);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Calculates the amount of days to the event.
     * @param date  date to be queried
     * @return  The days to event (positive signifies deadline in future).
     */
    public long daysToEvent(LocalDate date) {
        if (date.isBefore(this.startTime)) {
            return ChronoUnit.DAYS.between(date, this.startTime);
        } else if (date.isAfter(this.endTime)) {
            return ChronoUnit.DAYS.between(date, this.startTime);
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
       return String.format("[E]%s %s (from: %s to: %s)", super.formattedStatus(), super.task,
               super.formattedDate(this.startTime), super.formattedDate(this.endTime));
    }

    @Override
    public String toDataString() {
        return "E | " + (this.isCompleted ? "1" : "0") + " | " + this.task + " | "
                + this.startTime + " | " + this.endTime;
    }
}
