
/**
 * Encapsulates a Task.
 */
class Task {
    /**
     * Status of the Task.
     */
    boolean completed = false;

    /**
     * Details of the Task.
     */
    String task;

    public Task(String task) {
        this.task = task;
    }

    /**
     * Provides Details of the Task.
     * @return String detail message of Task.
     */
    public String provideDetails() {
        return completed ? "[x] " + task
                : "[ ] " + task;
    }

    /**
     * Flips the status of the Task.
     */
    public void updateTask() {
        this.completed = !this.completed;
    }

}
