package task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String by, String desc) {
        super(desc);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
