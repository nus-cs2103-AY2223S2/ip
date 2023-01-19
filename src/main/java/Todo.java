public class Todo extends Task {
    public Todo(String objective) {
        super(objective);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
