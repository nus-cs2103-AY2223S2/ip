public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        super.type = 'D';
    }

    @Override
    public String taskInFileFormat() {
        return super.taskInFileFormat() + " | " + by;
    }

    @Override
    public String toString() {
        return "[" + super.type + "]" + super.toString() + " (by: " + by + ")";
    }
}
