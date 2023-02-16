package duke;

/**
 * Task class
 */
public class Task {
    protected static final String DIVIDER = "/!@#&/";
    protected String description;
    protected boolean isDone;

    private String priority = " ";

    /**
     * Constructor for instantiating a Task object
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task
     * @return String completion status
     */
    public String getStatusIcon() {
        return (isDone ? "X": " ");
    }

    /**
     * Returns the description of the task
     * @return String description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the priority of the task
     * @return String priority
     */
    public String getPriority() {
        return this.priority;
    }

    public void assignPriority(String priority) {
        try {
            if (priority.equals("low") || priority.equals("medium") || priority.equals("high")) {
                this.priority = priority;
            } else {
                throw new DukeException("Please input a valid priority: low / medium / high");
            }
        } catch (DukeException e) {
            e.getMessage();
        }
    }

    /**
     * Marks the task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of a Task
     * @return String string representation of a task, which includes completion status and description
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns string representation of a task to be stored in the data.txt file
     * @return String string representation of a task to be stored in the data.txt file
     */
    public String toStorageData() {
        return this.toString();
    }
}
