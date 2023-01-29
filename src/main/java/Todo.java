public class Todo extends Task {
    public Todo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSavedString() {
        return "T|" + super.toSavedString();
    }
}
