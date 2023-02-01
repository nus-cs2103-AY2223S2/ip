package windycall;

public class Todo extends Task {

    public Todo(String description, boolean status) {
        super(description, status);
    }

    @Override
    public String getTaskTypeIcon() {
        return "T";
    }

    @Override
    public String getFileFormat() {
        return "T | " + getStatusIcon() + " | " + description + "\n";
    }

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + getCurrentDescription();
    }

}
