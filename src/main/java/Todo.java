public class Todo extends Task {
    private static final String taskType = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return taskType + super.toString();
    }
}
