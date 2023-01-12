public class Todo {
    private String task;
    private boolean isCompleted;

    public Todo(String task) {
        this.task = task;
        this.isCompleted = false;
    }

    /**
     * Gets the task content.
     * @return  The task content.
     */
    public String getTask() {
        return task;
    }

    /**
     * Toggles todo as completed.
     */
    public void markCompleted() {
        this.isCompleted = true;
    }

    /**
     * Toggles todo as uncompleted.
     */
    public void unmarkCompleted() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
