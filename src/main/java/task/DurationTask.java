package task;

/**
 * Class for a duration type task.
 */
public class DurationTask extends Task {
    private final int hours;
    private final int mins;

    /**
     * Constructor for duration type task.
     *
     * @param name Name of the task.
     * @param hours Number of hours allocated to task.
     * @param mins Number of minutes allocated to task.
     */
    public DurationTask(String name, int hours, int mins) {
        super(name);
        this.hours = hours;
        this.mins = mins;
    }

    /**
     * Constructor for duration task when loaded in from hard drive.
     *
     * @param name Name of the task.
     * @param hours Number of hours allocated to task.
     * @param mins Number of minutes allocated to task.
     * @param isDone True if task has been marked as done, false otherwise.
     */
    public DurationTask(String name, int hours, int mins, boolean isDone) {
        super(name, isDone);
        this.hours = hours;
        this.mins = mins;
    }

    @Override
    public String toString() {
        return "[Du] "
                + super.toString() + " ( "
                + hours + " hours " + mins + " mins )";
    }
}
