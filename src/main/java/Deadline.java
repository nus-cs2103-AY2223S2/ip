public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toText() {
        int done = -1;
        if (this.isDone) {
            done = 1;
        } else {
            done = 0;
        }
        return "D" + " | " + done +  " | " + this.description + " | " + this.by;
    }
}