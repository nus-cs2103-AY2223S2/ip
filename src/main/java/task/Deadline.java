package task;
public class Deadline extends Task {

    public String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean marked) {
        super(description, marked);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " |by: " + by;
    }
}
