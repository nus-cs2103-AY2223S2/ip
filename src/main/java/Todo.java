
/**
 * Contains information of a todo
 * Contains description of the todo
 */
public class Todo extends Task {

    private static final String TASK_TYPE = "T";

    /**
     * Creates a todo object
     *
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a todo object
     *
     * @param description Description of the todo task
     * @param isDone Completion status of the todo task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Creates a todo object from user input
     * Handles exceptions
     *
     * @param input Input from user
     * @return ToDo Task object
     * @throws DukeException If description of the task is empty
     */
    public static Todo generate(String input) throws DukeException {
        DukeException.ErrorType errType = DukeException.ErrorType.TODO;

        // Cleans input command
        input = input.trim();

        // Checks format of input command
        int index = input.indexOf(" ");
        if (index < 0) {
            throw new DukeException(errType, "Empty description");
        }

        // Cleans and checks variables
        String description = input.substring(index + 1).trim();
        if (description.equals("")) {
            throw new DukeException(errType, "Empty description");
        }

        return new Todo(description);
    }

    /**
     * Returns todo task from save string
     *
     * @param description Description of the todo task
     * @param status Completion status of the todo task
     * @return Todo task in save string format
     */
    public static Todo load(String description, String status) {
        boolean isDone = status.equals("X");
        return new Todo(description, isDone);
    }

    /**
     * Returns type of task, completion status, description of the task
     *
     * @return Type of task, completion status, description of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns todo task in save string format
     *
     * @param divider Divider used to separate fields
     * @return Task in save string format
     */
    @Override
    public String toSave(String divider) {
        return TASK_TYPE
                + divider + getStatusIcon()
                + divider + description;
    }
}
