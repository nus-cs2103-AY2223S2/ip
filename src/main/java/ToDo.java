/**
 * A task subclass to represent a ToDo (no date).
 */
public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}