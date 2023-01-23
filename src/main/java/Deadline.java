public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline target = (Deadline) o;
            return target.description.equals(this.description) && target.by.equals(this.by);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
