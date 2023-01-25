public class Deadline extends Task{

    private String deadline;

    public Deadline(String description, boolean status, String deadline) {
        super(description, status);
        this.deadline = deadline;
    }

    @Override
    public String getTaskTypeIcon() {
        return "D";
    }

    @Override
    public String fileFormat() {
        return "D | " + getStatusIcon() + " | " + description + " | " + deadline + "\n";
    }

    private String getDeadline() {
        return " (by: " + this.deadline + ")";
    }

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + getCurrentDescription() + this.getDeadline();
    }
}
