package duke;

/**
 * Pairs a Task with its index number in the TaskList.
 */
public class TaskNumberPair {
    private Task task;
    private int number;

    /**
     * Returns a TaskNumberPair given a Task and its index
     */
    public TaskNumberPair(Task task, int number) {
        this.task = task;
        this.number = number;
    }

    /**
     * Returns the Task.
     */
    public Task getTask() {
        return this.task;
    }

    /**
     * Returns the index of the Task.
     */
    public int getNumber() {
        return this.number;
    }
}
