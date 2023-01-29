package task;

/**
 * Task component representing a todo task.
 */
public class Todo extends Task {
    public Todo(String task) {
        super(task, false);
    }

    public Todo(String task, boolean isCompleted) {
        super(task, isCompleted);
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s", super.getFormattedStatus(), super.task);
    }

    @Override
    public String toDataString() {
        return "T | " + (this.isCompleted ? "1" : "0") + " | " + super.task;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
