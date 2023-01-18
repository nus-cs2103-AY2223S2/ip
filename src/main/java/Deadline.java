/**
 * This class represents a deadline Task that can be kept track of,
 * having a deadline associated with the task.
 *
 * @version CS2103T AY22/23 Sem 2 Individual Project
 * @author A0233828Y Eugene Tang
 */
public class Deadline extends Task {
    private String deadlineOfTask;

    /**
     * Constructs a Deadline task.
     * @param taskName The name of the task.
     * @param deadlineOfTask The deadline for this given deadline task.
     */
    public Deadline(String taskName, String deadlineOfTask) {
        super(taskName);
        this.deadlineOfTask = deadlineOfTask;
    }


    /**
     * Gets the status of the deadline task.
     * @return a String indicating the type, status and deadline for the task.
     */
    @Override
    public String getStatusOfTaskInString() {
        String typeOfTask = "D";
        return (this.isDone)
                ? "[" + typeOfTask + "][X] " + this.taskName + (" (by: ") + this.deadlineOfTask + ")"
                : "[" + typeOfTask + "][ ] " + this.taskName + (" (by: ") + this.deadlineOfTask + ")";
    }
}
