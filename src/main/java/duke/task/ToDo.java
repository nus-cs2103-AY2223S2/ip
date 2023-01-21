package duke.task;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
