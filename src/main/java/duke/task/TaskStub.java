package duke.task;

/**
 * Simplified <code>Task</code> class for testing.
 */
public class TaskStub extends Task {
    /**
     * Constructor for <code>TaskStub</code>.
     */
    public TaskStub() {
        super("description");
    }
    /**
     * Get simplified store format of a <code>Task</code>.
     * @return Simplified store format of a <code>Task</code>.
     */
    @Override
    public String getFileFormatString() {
        //to be split using "@"
        return "T" + "@" + "[]" + "@" + "description";
    }
}
