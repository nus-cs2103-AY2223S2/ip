public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean bool, String by) {
        super(description, bool);
        this.by = by;
    }

    @Override
    public Deadline markAsDone() {
        return new Deadline(description, true, by);
    }

    @Override
    public Deadline unmarkAsDone() {
        return new Deadline(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
