public class Todo extends Task {
    public Todo(String cont) {
        super(cont);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}