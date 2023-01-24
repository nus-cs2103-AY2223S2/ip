public class Deadline extends Task {
    protected final String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.symbol = "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String asDataFormat() {
        return super.asDataFormat("by:" + this.deadline);
    }
}
