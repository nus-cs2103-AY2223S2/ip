package task;
/**
 * Task is the superclass of the activities that can be stored within Duke.
 */
public abstract class Task {
    private String taskName;
    public boolean status;

    /**
     * Constructs a Task with the provided name and status.
     *
     * @param name The name of this task
     * @param status The status of this task
     */
    protected Task(String name, Boolean status){
        this.taskName = name;
        this.status = status;
    }

    /**
     * Constructs a Task with the provided name.
     * The task created would have its status set to false.
     *
     * @param name The name of this task
     */
    protected Task(String name) {
        this(name,false);
    }

    /**
     * Returns the name of the Task.
     *
     * @return The name of this Task.
     */
    protected String name(){
        return this.taskName;
    }

    /**
     * Returns the state of the task alongside with the task name.
     *
     * Example output:
     * > `[X] read book` would mean that the task `read book` is complete.
     * > `[ ] read book` would mean that the task `read book` is incomplete.
     *
     * @return The String representation of this task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.status ? "X" : " ", this.taskName);
    }

    /**
     * Returns the String representation of the task delimited by commas.
     *
     * @return The string representation of this task in CSV format
     */
    public abstract String toCSV();

}
