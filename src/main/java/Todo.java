public class Todo extends Task {
    private static final String taskType = "[T]";

    public Todo(String description) throws MissingDescriptionException {
        super(description);
    }

    @Override
    public String toString() {
        return taskType + super.toString();
    }

    @Override
    public String toStorageData() {
        String completed = getStatusIcon();
        return taskType + "//" + completed + "//" + description;
    }
}
