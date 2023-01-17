/**
 * The class representing a Deadline task.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class TaskDeadline extends DukeTask{
    private String deadline;

    /**
     * Given a task and a deadline, constructs a Deadline task.
     *
     * @param task The task to be completed.
     * @param deadline The task's deadline.
     */
    public TaskDeadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s}", this.deadline);
    }
}
