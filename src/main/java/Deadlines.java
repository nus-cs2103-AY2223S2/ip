public class Deadlines extends Task {

    protected String deadline;
    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline.substring(3);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadline);
    }
}
