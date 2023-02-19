package duke.task;

/**
 * Stores the description of a task and whether the task is done.
 */
public class Task implements Comparable<Task> {
    private boolean isDone;
    private final String task;

    /**
     * Creates a new task.
     * @param task The task.
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getTask() {
        return task;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + task;
    }

    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + task;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            return task.equals(((Task) obj).task) && isDone == ((Task) obj).isDone;
        }
        return false;
    }

    @Override
    public int compareTo(Task o) {
        if (isDone && !o.isDone) {
            return 1;
        } else if (!isDone && o.isDone) {
            return -1;
        }
        return task.compareTo(o.task);
    }

}
