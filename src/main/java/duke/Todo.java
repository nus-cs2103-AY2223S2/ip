package duke;

public class Todo extends Task {
    private static final String TASK_TYPE = "[T]";

    public Todo(String description) throws MissingDescriptionException {
        super(description);
    }

    @Override
    public String toString() {
        return TASK_TYPE + super.toString();
    }

    @Override
    public String toStorageData() {
        String completionStatus = getStatusIcon();
        return TASK_TYPE + "//" + completionStatus + "//" + description;
    }
}
