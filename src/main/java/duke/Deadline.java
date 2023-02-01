package duke;

public class Deadline extends Task {
    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    public String getFileRepresentation() {
        return "deadline|" + super.getFileRepresentation() + "|" + this.by.asFileDate();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
