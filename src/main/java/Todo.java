public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon();
    }

    @Override
    public String toRecord() {
        return "T|" + super.toRecord();
    }
}
