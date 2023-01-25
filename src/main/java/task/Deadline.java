package task;
public class Deadline extends Task {

    private String endDate;

    public Deadline(String task, String endDate) {
        super(task, false);
        this.endDate = endDate;
    }

    public Deadline(String task, boolean isCompleted, String endDate) {
        super(task, isCompleted);
        this.endDate = endDate;
    }

    @Override
    public String getTaskType() {
        return "Deadline";
    }

    @Override
    public String getStatus() {
        return String.format("%s", isCompleted());
    }

    @Override
    public String getDescription() {
        return getTask() + " | " + endDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endDate + ")";
    }
}
