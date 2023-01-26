package duke.task;

/**
 * Class to represent the Task created by user
 */
public class Task {
    private boolean isCompleted = false;
    private String taskName;

    public Task (String name) {
        this.taskName = name;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Change the status of Task to state
     *
     * @param state State of Task to be changed into
     */
    public void setIsCompleted(boolean state) {
        this.isCompleted = state;
    }
}
