package task;

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

    /**
     * Returns the formatted string to store in datafile.
     * @return  A formatted string for storage in datafile.
     */
    public abstract String toDataString();
}
