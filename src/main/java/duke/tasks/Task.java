package duke.tasks;

public abstract class Task {
    private TaskStatus status;
    private int id;

    public Task(int id) {
        status = TaskStatus.Pending;
        this.id = id;
    }

    public boolean isCompleted() {
        return status == TaskStatus.Completed;
    }

    public void markCompleted() {
        status = TaskStatus.Completed;
    }

    public void markPending() {
        status = TaskStatus.Pending;
    }

    public int id() {
        return this.id;
    }

    public abstract String description();

    public abstract String serialize();

    public static Task deserialize(String s) {
        switch (s.charAt(0)) {
            case 'T':
                return Todo.deserialize(s);
            case 'D':
                return Deadline.deserialize(s);
            case 'E':
                return Event.deserialize(s);
            default:
                return null;
        }
    }
}