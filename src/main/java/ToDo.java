public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getSaveTaskString() {
        return toString();
    }
    @Override
    public String toString() {
        return String.format("T | %s", super.toString());
    }
}
