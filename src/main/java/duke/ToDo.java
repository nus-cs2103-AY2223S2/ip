package duke;

/**
 * Represents a task that is of type "todos" and extends from Task class.
 *
 * @author MrTwit99
 * @since 2023-02-01
 */
public class ToDo extends Task {

    /**
     * Returns a ToDos task object that stores information about the task description.
     *
     * @param taskInfo Task Description
     */
    public ToDo(String taskInfo) {
        super(taskInfo);
    }

    /**
     * Returns a string on the information about the ToDos task that is to be added to the ongoing taskList.
     *
     * @return String message of the ToDos task description and status..
     */
    @Override
    public String getTaskInfoStatus() {
        return "[T]" + super.getTaskInfoStatus();
    }

    /**
     * Returns a string on the information about the ToDos task that is to be saved to the file allocated by Storage.
     *
     * @return String message of the ToDos task description and status.
     */
    @Override
    public String getTaskInfo() {
        return "[T]" + super.getTaskInfoStatus();
    }

    /**
     * Return boolean value indicating the task is ONGOING as it is a ToDo task.
     *
     * @return Boolean value indicating the task is ONGOING as it is a ToDo task.
     */
    @Override
    public boolean isOngoing() {
        return true;
    }

    /**
     * Returns a boolean value indicating the completion status of the Task.
     *
     * @return Boolean value indicating the completion status of the Task.
     */
    public boolean getStatus() {
        return super.getStatus();
    }
}
