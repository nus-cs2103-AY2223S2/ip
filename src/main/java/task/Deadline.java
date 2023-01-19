package task;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{
    private LocalDate deadline;

    public Deadline(String task, LocalDate deadline) {
        super(task, false);
        this.deadline = deadline;
    }

    /**
     * Calculates the amount of days to the deadline.
     * @param date  date to be queried
     * @return  The days to deadline (positive signifies deadline in future).
     */
    public long daysToDeadline(LocalDate date) {
        return ChronoUnit.DAYS.between(date, this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", super.formattedStatus(), super.task,
                super.formattedDate(this.deadline));
    }
}
