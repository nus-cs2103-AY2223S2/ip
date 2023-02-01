package duke;

public class Todo extends Task {
    public Todo(String description) {

        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    public String getSaveString() {
        return String.format("todo / %s / %s", super.getSaveString(), this.getDescription());
    }
}
