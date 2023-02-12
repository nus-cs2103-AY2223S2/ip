package duke.task;

/**
 * A Task class describing the tasks and keeping track of the task status whether is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task object
     *
     * @param description the data to be stored
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * The status icon X or no icon if the task is done or not done
     *
     * @return a string of X or nothing
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * A method to mark the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * A method to unmark the task
     */
    public void unMark() {

        this.isDone = false;
    }

    public boolean find(String word) {
        if (word.equals(this.description)) {
            return true;
        }
        return false;
    }

    /**
     * A toString method of the format [x] todo_task
     *
     * @return a string of above format
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


}
