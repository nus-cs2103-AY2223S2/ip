public class TaskDeadline extends DukeTask{
    private String deadline;
    public TaskDeadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s}", this.deadline);
    }
}
