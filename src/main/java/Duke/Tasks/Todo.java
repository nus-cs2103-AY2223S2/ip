package Duke.Tasks;

public class Todo extends Task {

    /**
     * Constructor of Todo class, uses to create Todo instance.
     *
     * @param description The description or the name of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Print the task into the specific file.
     *
     * @return Return the information of the task.
     */
    @Override
    public String printTask() {
        return String.format("T | %d | %s ", isComplete ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
