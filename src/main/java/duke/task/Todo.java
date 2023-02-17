package duke.task;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String getFileDesc() {
        return "T|" + super.getFileDesc();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}