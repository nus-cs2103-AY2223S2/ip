public class Todo extends Task {

    // Attributes:

    // Constructor:
    public Todo(String userInput) {
        super(userInput.substring(5));
    }

    // Methods:
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}