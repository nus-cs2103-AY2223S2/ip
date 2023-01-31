public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws EmptyDescriptionException {
        super(description);
        this.by = by;
    }
    @Override
    public String getFileDescription() {
        return "D | " + super.getStatusIcon() + " | " + super.description + " | "  + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
