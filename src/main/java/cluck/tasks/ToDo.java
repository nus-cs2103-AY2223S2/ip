package cluck.tasks;


/**
 * To do class is a task that only stores the task description and whether it is checked.
 */
public class ToDo extends Task {

    /**
     * Instantiates a new To do.
     *
     * @param description Task description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Instantiates a new To do, but To Do is manually set to be either marked or un-marked.
     *
     * @param isMarked    Boolean value of true if task is marked, false otherwise.
     * @param description Task description.
     */
    protected ToDo(boolean isMarked, String description) {
        super(isMarked, description);
    }

    @Override
    public String makeSaveFormat() {
        return "T" + super.makeSaveFormat() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
