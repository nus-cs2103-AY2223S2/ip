package duke;
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getTaskIcon() {
        return "T";
    }

    public String toSave() {
        return this.getTaskIcon() + SEPARATOR + convertBoolean() + SEPARATOR + this.getDescription();
    }
    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "]" + this.getStatusIcon() + " " + this.getDescription();
    }
}
