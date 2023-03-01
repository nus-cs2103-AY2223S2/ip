package duke.task;

import java.util.regex.PatternSyntaxException;

/**
 * Represents a Task, from which all other tasks inherit from
 */
public class Task {
    private final String task;
    private boolean isDone;

    /**
     * Returns a Task with task stored that is not done
     *
     * @param task String of task to be stored
     */
    public Task(String task) {
        this(task, false);
    }

    /**
     * Returns a Task with task and isDone stored
     *
     * @param task String of task to be stored
     * @param isDone boolean of if the task is done
     */
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    /**
     * Returns true if the task is done and false otherwise
     *
     * @return isDone boolean
     */
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

    /**
     * Returns the String task without any formatting
     *
     * @return task String
     */
    protected String getTask() {
        return this.task;
    }
    /**
     * Returns the String of the task formatted to be displayed
     *
     * @return String formatted String
     */
    @Override
    public String toString() {
        String markedAsDone = isDone ? "X" : " ";
        return String.format("[%s] %s", markedAsDone, task);
    }

    /**
     * Returns the String of the task used to be saved
     *
     * @return String formatted String
     */
    public String saveString() {
        int done = isDone() ? 1 : 0;
        return String.format("Task | %d | %s", done, task);
    }
}
