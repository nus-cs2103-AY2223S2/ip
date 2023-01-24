package duke.taskType;

/**
 * The class for the Todo tasks.
 */
public class Todo extends Task {
    /**
     * The constructor for Todo.
     *
     * @param cont the content of the task
     */
    public Todo(String cont) {
        super(cont);
    }

    /**
     * Override the toString() method to show the information of the Todo task.
     *
     * @return a String that shows the information of the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}