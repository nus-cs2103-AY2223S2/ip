public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getFileRepresentation() {
        return "D" + "@" + (super.isDone() ? "1" : "0") + "@" + this.getName() + "@" + this.by;
    }
 }
