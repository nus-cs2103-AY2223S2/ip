package duke.tasks;

/**
 * Represents Task object created according to users' input.
 * Extended by Todo, Deadline amd Event classes.
 */
public class Task {
    /**
     * Description of the task.
     */
    final String description;
    /**
     * Indicates the completion status of the task.
     */
    private boolean isDone;

    /**
     * Creates Task object with corresponding description
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon according to the completion status
     * of the task.
     *
     * @return Status icon that indicates the completion status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks current task as done.
     */
    public void markDone() {
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + this);
    }

    /**
     * Marks current task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + this);
    }

    /**
     * Generates a data string representation of the Task object. It will be used
     * to store the Task object in storage file.
     *
     * @return Data string of the task
     */
    public String getData() {
        StringBuilder sb = new StringBuilder();
        sb.append("T | ");
        if (isDone) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }
        sb.append(this.description).append("\n");
        return sb.toString();
    }

    /**
     * Returns completion status of current task
     *
     * @return Boolean value that states the completion status of the task
     */
    public boolean isTaskDone() {
        return isDone;
    }

    /**
     * Returns string representation of the Task object.
     *
     * @return String representation of the Task object
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
