package task;

public class DeadlineTask extends Task{
    private String deadline;

    public DeadlineTask(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline == null ? "[none]" : this.deadline);
    }
}
