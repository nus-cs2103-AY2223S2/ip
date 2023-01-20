package task;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String getFileRepresentation() {
        return "T" + "@" + (super.isDone() ? "1" : "0") + "@" + this.getName();
    }
}
