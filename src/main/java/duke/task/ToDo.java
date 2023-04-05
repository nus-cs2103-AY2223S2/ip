package duke.task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, String status, String tag) {
        super(name, status, tag);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String asTokens() {
        return "T," + super.asTokens();
    }
}
