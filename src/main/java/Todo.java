
/**
 * Contains information of a todo
 * Contains description of the todo
 */
public class Todo extends Task {

    /**
     * Creates a todo object
     *
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
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
        // Cleans input command
        input = input.trim();

        // Checks format of input command
        int index = input.indexOf(" ");
        if (index < 0) {
            throw new DukeException("ToDo", "Empty description");
        }

        // Cleans and checks variables
        String description = input.substring(index + 1).trim();
        if (description.equals("")) {
            throw new DukeException("ToDo", "Empty description");
        }

        return new Todo(description);
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
}
