public class Deadline extends Task{
    private String by;
    public Deadline(String taskName, String by, int taskNumber) {
        super(taskName, taskNumber);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.by + ")";
    }
}
