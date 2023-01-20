public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    String getBy() {
        return by;
    }

    void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toCsv() {
        return "D," + super.toCsv() + "," + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (By: " + by + ")";
    }
}
