/**
 * Represents a to-do Task that can be kept track of, having no date or time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo task.
     *
     * @param taskName Name of the ToDo task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Gets the status of the task with the task name.
     *
     * @return a String indicating the type and status of the task.
     */
    @Override
    public String getStatusOfTaskInString() {
        String typeOfTask = "T";
        return (this.isDone)
               ? "[" + typeOfTask + "][X] " + this.taskName
               : "[" + typeOfTask + "][ ] " + this.taskName;
    }

}
