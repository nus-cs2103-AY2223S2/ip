package duke.task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, String status) {
        super(name, status);
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
