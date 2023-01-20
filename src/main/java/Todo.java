public class Todo extends Task {

    // Attributes:

    // Constructor:
    public Todo(String user_input) {
        super(user_input.substring(5));
    }

    // Methods:
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}