public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        // [D][ ] return book (by: June 6th)
        return "[D]" + " " + super.getStatusIcon() + " " + super.toString() + " (by: " + by + ")";
    }
}
