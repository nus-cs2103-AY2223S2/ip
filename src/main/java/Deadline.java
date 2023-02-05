public class Deadline extends Task {
    private String deadline;
    public Deadline(String cmd) {
        this(cmd.split(" /by")[0],
                cmd.split(" /by")[1]);
    }
    Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }
    /**
     * Return the String for Deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by:%s)", super.toString(), deadline);
    }
}
