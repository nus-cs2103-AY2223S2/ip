package treebot.props;

/**
 * Represents the props needed to create a Task.
 */
public abstract class Props {

    protected String taskDescription;

    /**
     * Returns the task description prop.
     * @return
     */
    public String getTaskDescription() {
        return taskDescription;
    }
}
