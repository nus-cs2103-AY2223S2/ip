public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + this.by + ")";
    }

    @Override
    public String saveString() {
        return String.format("D|%s|%s|%s", super.saveString(), super.description, this.by);
    }
}
