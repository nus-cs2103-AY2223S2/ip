package duke.task;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
