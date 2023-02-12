package duke;

/**
 * Class that defines the Todo type of tasks
 */
public class Todo extends Task {

    /**
     * Constructor for Todo objects
     *
     * @param userInput Specifies the todo task's title
     */
    public Todo(boolean isCompleted, String userInput) {
        super(isCompleted, userInput,TaskType.T);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}