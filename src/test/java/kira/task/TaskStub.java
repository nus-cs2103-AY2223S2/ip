package kira.task;

/**
 * Dummy Task class for testing purposes.
 */
public class TaskStub extends Task {

    private int taskIndex;

    /**
     * Creates a dummy task for testing.
     */
    public TaskStub(int index) {
        super("test");
        this.taskIndex = index;
    }

    public int getIndex() {
        return taskIndex;
    }

}
