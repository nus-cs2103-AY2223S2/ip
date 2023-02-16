package items;

/**
 * Represents a to do task
 * @author clydelhui
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description, "T");
    }

    public ToDo(String description, boolean isDone) {
        super(description, "T", isDone);
    }

    @Override
    public String generateStorageForm() {
        return this.getTaskType() + "@" + this.getDescription() + "@" + this.getStatusIcon();
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]" + "[" + this.getStatusIcon() + "]" + this.description;
    }
}
