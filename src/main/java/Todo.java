public class Todo extends Task {

    // Attributes:

    // Constructor:
    public Todo(String title) {
        super(title);
    }

    // Methods:
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}