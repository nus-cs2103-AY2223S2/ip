public class Deadline extends Task {
    protected String by;

    public Deadline(String description) {
        super(description.split("/")[0]);
        this.by = description.split("/")[1].substring(3);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
