public class Deadline extends Task {
    private final String typeOfTask = "D";
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
        return (this.isDone)
                ? "[" + typeOfTask + "][" + "[X] " + this.taskName + (" (by: ") + this.deadlineOfTask + ")"
                : "[" + typeOfTask + "][" + "[] " + this.taskName + (" (by: ") + this.deadlineOfTask + ")";
    }
}
