package henz.tasks;

/**
 * Todo class extends the Task class.
 * @author Leng Wei Cong, Justin
 */
public class Todo extends Task {

    /**
     * Constructor.
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo Object.
     * @return a string in the format "[T] description"
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
