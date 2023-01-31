public class Deadline extends Task {
    private String doBy;

    public Deadline (String description, String deadline) {
        super(description);
        this.doBy = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + this.doBy + ")";
    }
}
