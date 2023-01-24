package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
