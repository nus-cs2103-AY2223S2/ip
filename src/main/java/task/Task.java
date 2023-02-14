package task;

/**
 * This class represents a task.
 * A task has a name and a mark indicating whether it's done or not.
 */
public class Task {
    static final String DIVIDER = " | ";
    private boolean isMarked;
    private String name;

    /**
     * Constructs a new task with the given name.
     * @param name the name of the task
     */
    public Task(String name) {
        this.isMarked = false;
        this.name = name;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isMarked = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmark() {
        isMarked = false;
    }

    /**
     * Returns the status icon of the task, either "X" or " ".
     * @return the status icon of the task
     */
    public String getStatusICon() {
        return (isMarked ? "X" : " ");
    }

    public boolean contains(String keyword) {
        return this.name.contains(keyword);
    }

    /**
     * Returns the task in a format suitable for saving.
     * @return the task in a format suitable for saving
     */
    public String toSaveFormat() {
        String markToInt = this.isMarked ? "1" : "0";
        return DIVIDER + markToInt + DIVIDER + name;
    }

    /**
     * Returns a string representation of the task.
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusICon() + "] " + this.name;
    }

}
