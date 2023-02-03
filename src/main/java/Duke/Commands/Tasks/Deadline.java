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

    private String getTaskClass() {
        return "D";
    }

    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.getTaskClass(), this.getStatusIcon(), this.description, this.deadline);
    }
}
