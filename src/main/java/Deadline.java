public class Deadline extends Task {
    private String taskName;
    private String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.taskName = taskName;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
