public class Deadline extends Task{
    protected String by;

    public Deadline(String TaskName, String by) {
        super(TaskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatus() + "] " + super.toString()
                + " (by: " + this.by + ")";
    }
}
