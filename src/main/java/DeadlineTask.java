public class DeadlineTask extends Task {

    private final String by;

    DeadlineTask(String name, String by) {
        super(name);
        this.by = by;
    }

    public String toDukeFileString() {
        return "D|" + super.toDukeFileString() + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
