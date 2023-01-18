/**
 * This class represents a to-do Task that can be kept track of,
 * having no date or time attached to it.
 *
 * @version CS2103T AY22/23 Sem 2 Individual Project
 * @author A0233828Y Eugene Tang
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo task.
     * @param taskName Name of the ToDo task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Gets the status of the task with the task name.
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
