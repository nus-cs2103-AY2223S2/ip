public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskInline() {
        return "[D]" + super.getTaskInline();
    }

    @Override
    public String getTaskInline(Integer index) {
        return index.toString() + ". [D]" + super.getTaskInline() + " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}
