public class Deadline extends Task{
    protected String by;
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
        this.type = "D";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String encode() {
        return String.format("%s | %s | %s | %s", this.type, this.isDone, this.taskName, this.by);
    }
}
