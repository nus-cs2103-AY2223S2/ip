public class Deadline extends Task {
    protected String by;

    Deadline(String title, String by) {
        super(title);
        this.by = by.replace("/by", "").trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
