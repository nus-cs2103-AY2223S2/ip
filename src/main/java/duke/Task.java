package duke;

/**
 * An abstract class that groups all categories of tasks that duke
 * is able to keep track of. All tasks have a description and also
 * a boolean value that indicates if the task is done already.
 */
public abstract class Task {
    private boolean done;
    private String task;
    private int priority; // 0 = High, 1 = Normal, 2 = Low priority.
    public static final String[] arr = new String[] {"High", "Normal", "Low"};

    public Task(String name, boolean done) {
        this.done = done;
        this.task = name;
        this.priority = 2;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public void markPriority(int prio) {
        this.priority = prio;
    }

    public String getPriority() {
        return arr[this.priority];
    }

    public boolean getDone() {
        return this.done;
    }

    public String getTask() {
        return this.task;
    }

    /**
     * Method to help keep track of tasks in duke.txt for future
     * references.
     *
     * @return String that represents the task to do.
     */
    public abstract String summary();

    @Override
    public String toString() {
        String checkmark = this.done ? "X" : " ";
        return String.format("[ %s ] %s priority: %s ", checkmark, this.task, this.arr[this.priority]);
    }
}
