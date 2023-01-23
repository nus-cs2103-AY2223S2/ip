public class Todo extends Task {
    public Todo(String details) {
        super(details);
    }

    @Override
    public String toString() {
        String task = super.toString();
        return "[T] " + task;
    }
}
