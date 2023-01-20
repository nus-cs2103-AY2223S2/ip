public class Deadline extends Task {
    private final String by;

    public Deadline(String str, String by) {
        super(str);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
