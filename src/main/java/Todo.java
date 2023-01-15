public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean bool) {
        super(description, bool);
    }

    @Override
    public Todo markAsDone() {
        return new Todo(description, true);
    }

    @Override
    public Todo unmarkAsDone() {
        return new Todo(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
