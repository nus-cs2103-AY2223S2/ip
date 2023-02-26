package duke.task;

import java.util.regex.PatternSyntaxException;

/**
 * task.Task used to hold the string representation of tasks to be done
 */
public class Task {
    private final String task;
    private boolean isDone;
    public Task(String task) throws PatternSyntaxException {
        this(task, false);
    }
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }
    public boolean isDone() {
        return this.isDone;
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
    protected String getTask() {
        return this.task;
    }
    /**
     * Returns the string of the task
     */
    @Override
    public String toString() {
        String markedAsDone = isDone ? "X" : " ";
        return String.format("[%s] %s", markedAsDone, task);
    }
    public String saveString() {
        int done = isDone() ? 1 : 0;
        return String.format("Task | %d | %s", done, task);
    }
}
