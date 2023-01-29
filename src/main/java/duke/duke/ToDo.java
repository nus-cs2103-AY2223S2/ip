package duke;

public class ToDo extends Task{
    private final String taskType = "[T]";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return taskType + super.toString();
    }
}
