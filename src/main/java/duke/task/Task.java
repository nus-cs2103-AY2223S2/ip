package duke.task;

/**
 * The task class.
 */
public class Task implements Comparable<Task> {
    private boolean done;
    private final String task;

    /**
     * Creates a new task.
     * @param task The task.
     */
    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public String getTask() {
        return task;
    }

    public void markAsDone() {
        done = true;
    }

    public void markAsNotDone() {
        done = false;
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + task;
    }

    public String toFileString() {
        return (done ? "1" : "0") + " | " + task;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            return task.equals(((Task) obj).task) && done == ((Task) obj).done;
        }
        return false;
    }

    @Override
    public int compareTo(Task o) {
        if (done && !o.done) {
            return 1;
        } else if (!done && o.done) {
            return -1;
        }
        return task.compareTo(o.task);
    }

}
