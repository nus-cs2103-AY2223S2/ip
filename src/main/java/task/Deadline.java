package task;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Task component representing a deadline task.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Deadline constructor.
     *
     * @param task a task string
     * @param deadline the deadline of the task
     */
    public Deadline(String task, LocalDate deadline) {
        super(task, false);
        this.deadline = deadline;
    }

    /**
     * Deadline constructor.
     *
     * @param task a task string
     * @param deadline the deadline of the task
     * @param isCompleted completion status
     */
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
        return String.format("[D]%s %s (by: %s)", super.getFormattedStatus(), super.getTask(),
                super.getFormattedDate(this.deadline));
    }

    @Override
    public String toDataString() {
        return "D | " + (super.getIsCompleted() ? "1" : "0") + " | " + super.getTask() + " | " + this.deadline;
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
