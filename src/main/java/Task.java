public class Task {
    private String taskName;
    //Status of the task
    private boolean isDone = false;

    /**
     * Constructs a user task.
     * @param taskName The name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Sets status of task to be done
     */
    public void setDoneStatus() {
        isDone = true;
    }

    /**
     * Sets status of task to be undone
     */
    public void setUndoneStatus() {
        isDone = false;
    }

    /**
     * Gets the status of the task with the task name.
     * @return a String in the form of either "[X] taskName" or "[ ] taskName"
     *         depending on the status of the task. X means done.
     */
    public String getStatusOfTaskInString() {
        String statusBrackets = (isDone)
                                ? "[X]" //Done
                                : "[ ]"; //Not done
        return statusBrackets + " " + taskName;
    }
}
