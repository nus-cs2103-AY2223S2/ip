public class Deadline extends Task {
    protected String by;

    Deadline(String title, String by) throws DukeException {
        super(title);
        this.by = by.replace("/by", "").trim();
    }

    Deadline(String title, String by, boolean done) throws DukeException {
        super(title, done);
        this.by = by.replace("/by", "").trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
