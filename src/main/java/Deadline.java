/**
 * Encapsulation of a Deadline task,
 * a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    //The deadline given.
    private String end;

    /**
     * Constructor for Deadline.
     * @param name The name of the task.
     */
    public Deadline(String name, String end) {
        super(name);
        this.end = end;
    }

    /**
     * Get the string with a [D] icon representing this task.
     * @return A string representation of this Deadline task.
     */
    @Override
    public String toString() {
        String toPrint = String.format("[D] %s (by: %s)", super.toString(), this.end);
        return toPrint;
    }
}
