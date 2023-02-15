package duke.tasks;

/**
 * Represents a task to be done. This task has no attributes other than its title.
 */
public class ToDo extends Task {

    public ToDo(String title, boolean isDone) {
        super(title, isDone);
    }

    /**
     * Returns formatted string representation of the ToDo.
     * This string includes the task completion status, type and name of task.
     *
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        String doneString = this.getDone() ? "X" : " ";
        return String.format("[T][%s] %s", doneString, this.getTitle());
    }

    /**
     * Returns formatted string representation of the ToDo, for storage in memory.
     * This string includes the task completion status, type and name of task.
     *
     * @return String representation of the ToDo, for storage in memory.
     */
    public String convertToMemoryString() {
        String doneString = this.getDone() ? "1" : "0";
        return "T, " + doneString + ", " + this.getTitle();
    }

}
