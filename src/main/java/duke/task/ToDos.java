package duke.task;


/**
 * A todo class for the todo command
 */
public class ToDos extends Task {
    /**
     * A constructor to create a todo object
     *
     * @param description the task to be kept
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * A task Icon of T represented for todo
     *
     * @return "T"
     */
    public String getTaskIcon() {
        return "T";
    }

    /**
     * A toString method of format [T] task_todo
     *
     * @return a string of above format
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
