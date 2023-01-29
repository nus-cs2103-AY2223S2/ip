package duke.task;

/**
 * A task subclass to represent a ToDo (no date).
 */
public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ToDo) {
            ToDo other = (ToDo) obj;
            return this.description.equals(other.description);
        }
        return false;
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