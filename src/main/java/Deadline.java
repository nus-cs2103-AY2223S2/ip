public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        by = by.replaceAll("by","");
        by = by.replaceAll("/","");
        this.by = by;
        Task.actions += 1;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
