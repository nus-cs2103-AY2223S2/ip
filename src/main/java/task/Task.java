package task;

import java.time.LocalDate;

/**
 * Class that store details of a task.
 */
public abstract class Task {
    private final String description;
    private boolean done;
    private Recurrence recurrence = Recurrence.NONE;

    enum Recurrence {
        NONE,
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY,
    }
    /**
     * Constructs Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        done = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task to be done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Sets the take to be not done.
     */
    public void markNotDone() {
        this.done = false;
    }

    /**
     * Returns whether the task is done.
     *
     * @return a boolean representing whether task is done.
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Set the recurrence status of the task to daily, weekly, monthly or yearly.
     *
     * @param r The recurrence status of the task.
     */
    public void setRecurrence(String r) {
        this.recurrence = Recurrence.valueOf(r.toUpperCase());
    }

    /**
     * Return the recurrence status of the task.
     *
     * @return Returns recurrence status of the task.
     */
    public String getRecurrence() {
        switch (recurrence) {
        case NONE:
            return " ";
        case DAILY:
            return "D";
        case WEEKLY:
            return "W";
        case MONTHLY:
            return "M";
        case YEARLY:
            return "Y";
        default:
            return null;
        }
    }

    /**
     * Get the next recurring date of a task.
     *
     * @param curDate The current date.
     * @return The date of the next recurrence.
     */
    public LocalDate getNextDate(LocalDate curDate) {
        switch (recurrence) {
        case DAILY:
            return curDate.plusDays(1);
        case WEEKLY:
            return curDate.plusWeeks(1);
        case MONTHLY:
            return curDate.plusMonths(1);
        case YEARLY:
            return curDate.plusYears(1);
        default:
            return null;
        }
    }

    /**
     * Mark the task as not done when the task get refreshed.
     */
    public void refresh() {
        markNotDone();
    }

    /**
     * Returns the description of the task to be written in file.
     *
     * @return Details of task.
     */
    public String toText() {
        return (isDone() ? 1 : 0) + "|" + getRecurrence() + "|" + getDescription();
    }

    /**
     * Returns the description of the task to be output to users.
     *
     * @return Details of task.
     */
    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "]" + "[" + getRecurrence() + "] " + this.description;
    }
}
