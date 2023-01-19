public class Deadline extends Task {
    private String deadline;

    /** 
     * A public constructor to initialize Deadline instance.
     * 
     * @param task Task name.
     * @param deadline Task deadline.
     */
    Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    /** 
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.deadline + ")";
    }
}