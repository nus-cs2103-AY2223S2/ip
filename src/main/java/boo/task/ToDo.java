package boo.task;

/**
 * Represents a to-do {@code Task} that can be kept track of, having no date or time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructs a new to-do task.
     *
     * @param taskName Name of the to-do task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Gets the status of the task with the task name.
     *
     * @return a {@code String} indicating the type and status of the task.
     */
    @Override
    public String getStatusOfTaskInString() {
        String typeOfTask = "T";
        return (this.isDone)
               ? "[" + typeOfTask + "][X] " + this.taskName
               : "[" + typeOfTask + "][ ] " + this.taskName;
    }

}
