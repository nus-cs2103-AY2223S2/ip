package duke;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toStorableString() {
        // T|0|return book
        return "T," +  (this.isDone() ? "1" : "0") + "," + this.getDescription();
    }
}