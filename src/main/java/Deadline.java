public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " (by: " + by + ")";
    }

    @Override
    public String toRecord() {
        return "D|" + super.toRecord() + "|" + by;
    }
}
