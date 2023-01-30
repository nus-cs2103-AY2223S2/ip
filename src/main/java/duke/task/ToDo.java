package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toTXT() { return "T | " + super.toTXT(); }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

