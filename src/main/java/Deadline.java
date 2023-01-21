/**
 * The Deadline class extends the Task class and represents a task that has a due date.
 */
public class Deadline extends Task {
    private static final long serialVersionUID = 102;

    private String date;

    /**
     * Constructs a Deadline Task with a due date.
     *
     * @param name The name of the task.
     * @param date The date of when the task is due.
     */
    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }

}
