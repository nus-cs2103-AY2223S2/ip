package task;

public class Task {

    private final String details;
    private boolean isCompleted = false;

    public Task(String details) {
        this.details = details;
    }

    /**
     * Returns the value of the private field 'details' representing the details of the task
     *
     * @return The current value of 'details'.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Returns the value of the private field 'isCompleted' representing the completion status of the task,
     * true if the task is completed, false otherwise.
     *
     * @return The current value of 'isCompleted'.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Changes the completion status of the task.
     *
     * @param hasCompleted The new completion status of the task,
     *                      true if the task is completed, false otherwise.
     */
    public void changeStatus(boolean hasCompleted) {
        if (hasCompleted) {
            isCompleted = true;
            return;
        }
        isCompleted = false;
    }
}
