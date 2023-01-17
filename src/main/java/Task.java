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
     * Toggles the status of the task.
     */
    public void toggleStatus() {
        isDone = ! isDone;
    }

    /**
     * Gets the status of the task with the task name.
     * @return a String in the form of either "[X] taskName" or "[ ] taskName"
     *         depending on the status of the task.
     */
    public String getStatusOfTask() {
        String statusBrackets = (isDone)
                                ? "[X]" //Done
                                : "[ ]"; //Not done
        return statusBrackets + " " + taskName;
    }




}
