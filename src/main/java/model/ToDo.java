package model;

public class ToDo extends Task {
    public static final String TAG = "[T]";

    public ToDo(String title) {
        super(title);
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    @Override
    public String getDeadline() {
        return Task.EMPTY;
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
        return ToDo.TAG + super.toString();
    }
}
