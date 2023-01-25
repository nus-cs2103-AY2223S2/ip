/**
 * Class that defines the Todo type of tasks
 */
public class Todo extends Task {

    /**
     * Constructor for Todo objects
     *
     * @param userInput Specifies the todo task's title
     */
    public Todo(String userInput) {
        super(userInput.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}