package Task;

public class Deadline extends Task {
    final private String deadline;

    /**
     * Constructor method for Deadline
     * @param task new task to be added
     * @param deadline deadline of the task
     */
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    public String toData() {
        String status = this.completed ? "1" : "0";
        return "D | " + status + " |" + this.task + "|" + this.deadline;
    }
    @Override
    public String toString() {
        String statusIcon = this.completed ? "X" : " ";
        return "[D][" + statusIcon + "]" + this.task + "(by:" + this.deadline + ")";
    }
}
