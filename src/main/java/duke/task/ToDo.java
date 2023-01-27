package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description, false, "T");
    }

    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }
}
