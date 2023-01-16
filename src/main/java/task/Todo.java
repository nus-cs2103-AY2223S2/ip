package task;

public class Todo extends Task {
    public Todo(String task) {
        this.task = task;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[T][X] " + this.task;
        } else {
            return "[T][ ] " + this.task;
        }
    }

    @Override
    public String toDataString() {
        return "T | " + (this.isCompleted ? "1" : "0") + " | " + this.task;
    }
}
