package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toCsv() {
        return "T," + super.toCsv();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
