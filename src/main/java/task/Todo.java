package task;

/**
 * Task component representing a todo task.
 */
public class Todo extends Task {
    /**
     * Todo constructor.
     *
     * @param task a task string
     */
    public Todo(String task) {
        super(task, false);
    }

    /**
     * Todo constructor.
     *
     * @param task a task string
     * @param isCompleted completion status
     */
    public Todo(String task, boolean isCompleted) {
        super(task, isCompleted);
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s", super.getFormattedStatus(), super.getTask());
    }

    @Override
    public String toDataString() {
        return "T | " + (super.getIsCompleted() ? "1" : "0") + " | " + super.getTask();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
