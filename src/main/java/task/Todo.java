package task;

public class Todo extends Task {
    public Todo(String task) {
        super(task, false);
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s", super.formattedStatus(), super.task);
    }

    @Override
    public String toDataString() {
        return "T | " + (this.isCompleted ? "1" : "0") + " | " + this.task;
    }
}
