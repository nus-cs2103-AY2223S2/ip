package duke.tasks;

/**
 * Represents a deadline
 */
public class Deadline extends Task {
    private String by;

    /**
     * Represents a deadline constructor
     * @param description task name
     * @param by deadline for the task
     * @param isDone check whether marks as done
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Overrides toString method
     * @return formatted message
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + "by: " + this.by + ")";
    }

    /**
     * Formats a deadline into a suitable String
     * @return formatted message
     */
    @Override
    public String file() {
        return "D | " + super.file() + " | " + this.by;
    }
}
