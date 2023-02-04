package Duke.Commands.Tasks;

public class Deadline extends Task {
    protected String deadline;
    public Deadline(String description, String deadline) {
        this(description, false, deadline);
    }

    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Generates a letter representing the type of task
     *
     * @return a letter representing the type of this task
     */
    public String getTaskClass() {
        return "D";
    }

    private String getDeadline() {
        return this.deadline;
    }

    /**
     * Generates a String to store this task in a local text file
     *
     * @return A representative String that contains data about the current task
     */
    public String generateStorageText() {
        return String.format("%s-%s-%s-%s",
                this.getTaskClass(), this.getStatusIcon(),
                this.getDescription(), this.getDeadline());
    }

    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.getTaskClass(), this.getStatusIcon(), this.description, this.deadline);
    }
}
