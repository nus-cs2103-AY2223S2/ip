public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String saveAsStr() {
        return "D" + super.saveAsStr() + "~%~" + by;
    }
}