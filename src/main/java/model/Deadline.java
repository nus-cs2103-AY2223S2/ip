package model;

public class Deadline extends Task {
    public static final String TAG = "[D]";

    private String deadline;

    public Deadline(String title, String deadline) {
        super(title);

        this.deadline = deadline;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String getStartDateTime() {
        return Task.EMPTY;
    }

    @Override
    public String getEndDateTime() {
        return Task.EMPTY;
    }

    @Override
    public String toString() {
        return Deadline.TAG + super.toString() + " (by: " + this.deadline + ")";
    }
}
