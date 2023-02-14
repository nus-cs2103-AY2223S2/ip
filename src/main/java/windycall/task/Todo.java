package windycall.task;

public class Todo extends Task {

    public Todo(String description, boolean status) {
        super(description, status);
    }

    public Todo(String description, boolean status, String tag) {
        super(description, status, tag);
    }

    @Override
    public String getTaskTypeIcon() {
        return "T";
    }

    @Override
    public String getFileFormat() {
        return "T | " + getStatusIcon() + " | " + tag + " | " + description + "\n";
    }

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + getCurrentDescription();
    }

}
