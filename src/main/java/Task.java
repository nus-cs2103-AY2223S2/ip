public abstract class Task {
    String task;
    boolean isCompleted;

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
}
