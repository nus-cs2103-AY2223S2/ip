package duke;
public class ToDo extends Task {
    public ToDo(String description) throws EmptyDescriptionException {
        super(description);
    }
    @Override
    public String getFileDescription() {
    return "T | " + super.getStatusIcon() + " | " + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
