package duke.tasks;

/**
 * Represents deadline tasks
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Deadline constructor
     * @param description task name
     * @param by deadline for the task
     * @param isInFile check whether is in saving file
     */
    public Deadline(String description, String by, boolean isInFile) {
        super(description);
        this.by = by;

        if (!isInFile) {
            System.out.println(" " + "____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + toString());
            System.out.println(" Now you have " + taskNum + " tasks in the list.");
            System.out.println(" " + "____________________________________________________________");
        }
    }

    /**
     * Override toString method
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription()
                + " (" + "by: " + this.by + ")";
    }

    /**
     * Override file method, changing into data saving format
     * @return String
     */
    @Override
    public String file() {
        String status = isDone ? "1" : "0";
        return "D | " + status + " | " + getDescription() + " | " + this.by;
    }
}
