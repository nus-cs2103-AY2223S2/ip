
public class Deadline extends Task{

    protected String by;
    protected boolean isSet;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getStatus() {
        return (isSet ? "D" : " "); // mark done task with D
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "]" + super.toString() + " (by: " + this.by + ")";
    }
}
