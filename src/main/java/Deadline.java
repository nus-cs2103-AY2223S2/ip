/**
 * A class that represents a Deadline with deadline time
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Initialize an Event object with the given values.
     *
     * @param name The name of the deadline
     * @param deadline The deadline time of the task
     * @return A deadline instance
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of the Deadline task, including
     * whether the task is done or not.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
