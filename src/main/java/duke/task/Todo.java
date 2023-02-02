package duke.task;

/**
 * Todo is a Task with no added variables.
 */
public class Todo extends Task {

    /**
     * Constructor of Todo class.
     * @param description Description of Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Generates Save format to write into data.txt file.
     * @return String format of Todo task.
     */
    @Override
    public String toSaveFormat() {
        return "T||" + super.toSaveFormat();
    }

    /**
     * String representation of Todo task.
     * @return String representation of Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a Todo object generated from parsing savedData string.
     * @param savedData String stored to represent a Todo task.
     * @return Todo task.
     */
    public static Todo fromSaveFormat(String savedData) {
        String[] inputs = savedData.split("\\|\\|");
        Todo generatedTodo = new Todo(inputs[2]);
        if (inputs[1].equals("1")) {
            generatedTodo.setCompleted(true);
        }
        return generatedTodo;
    }
}
