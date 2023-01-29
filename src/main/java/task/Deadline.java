package task;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Task component representing a deadline task.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String task, LocalDate deadline) {
        super(task, false);
        this.deadline = deadline;
    }

    public Deadline(String task, LocalDate deadline, boolean isCompleted) {
        super(task, isCompleted);
        this.deadline = deadline;
    }

    /**
     * Calculates the amount of days to the deadline.
     *
     * @param date date to be queried
     * @return The days to deadline (positive signifies deadline in future).
     */
    public long daysToDeadline(LocalDate date) {
        return ChronoUnit.DAYS.between(date, this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", super.getFormattedStatus(), super.task,
                super.getFormattedDate(this.deadline));
    }

    @Override
    public String toDataString() {
        return "D | " + (this.isCompleted ? "1" : "0") + " | " + super.task + " | " + this.deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deadline)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Deadline deadline1 = (Deadline) o;

        return deadline.equals(deadline1.deadline);
    }
}
