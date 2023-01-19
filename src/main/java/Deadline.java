public class Deadline extends Task {
    private String by;

    public Deadline(String objective, String by) {
        super(objective);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
