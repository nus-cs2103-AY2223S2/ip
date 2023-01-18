public class Deadline extends Task{
    protected String by;
    protected String taskType;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "D";
    }

    public String toString() {
        return "[" + this.taskType + "][" + super.status + "] "
                + super.description
                + "(by: " + this.by
                + ")";
    }
}
