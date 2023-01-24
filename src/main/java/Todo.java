public class Todo extends Task {

    public Todo(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
