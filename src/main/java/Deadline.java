public class Deadline extends Task {
    private String deadline;

    public Deadline(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String deadlineString = " (by: " + this.deadline + ")";
        return "[D]" + super.toString() + deadlineString;
    }

    @Override
    public String getFileWriteString() {
        return "D" + super.getFileWriteString() + " | " + this.deadline;
    }
}
