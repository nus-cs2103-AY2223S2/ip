public class TaskDeadline extends Task {
    public final String endTime;

    public TaskDeadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.endTime);
    }
}
