/**
 * Task used to hold the string representation of tasks to be done
 */
public class Task {
    private String task;
    private boolean isDone;
    Task(String task) {
        this(task, false);
    }
    Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }
    /**
     * Sets task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Sets task as not done
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the string of the task
     */
    @Override
    public String toString() {
        String markedAsDone = isDone ? "X" : " ";
        return String.format("[%s] %s", markedAsDone, task);
    }
}
