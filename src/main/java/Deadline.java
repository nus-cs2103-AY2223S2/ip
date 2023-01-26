public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description, false);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    protected String getTaskFileFormat() {
        return "D" + " | " + super.getTaskFileFormat() + " | " + by;
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
