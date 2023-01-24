package entities;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toSave() {
        if (super.isDone == true) {
            return String.format("D | 1 | %s | %s\n", super.getDescription(), this.by);
        }
        return String.format("D | 0 | %s | %s\n", super.getDescription(), this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
