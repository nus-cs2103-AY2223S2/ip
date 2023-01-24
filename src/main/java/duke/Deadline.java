package duke;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        by = by.replaceAll("by","");
        by = by.replaceAll("/","");
        this.by = by;
        this.description = description;
        Task.actions += 1;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
    @Override
    public String toSaveString() {
        return String.format(" deadline ||%s || %s || %s", super.toSaveString(), this.description, this.by);
    }
}
