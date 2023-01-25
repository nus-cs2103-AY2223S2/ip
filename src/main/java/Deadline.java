public class Deadline extends Task {

    protected String by;
    protected String type = "[D]";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return type + super.toString() + " (by: " + by + ")";
    }
}
