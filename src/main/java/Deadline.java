public class Deadline extends Task {
    private String doBy;

    public Deadline (String description, String deadline) {
        super(description);
        this.doBy = deadline;
    }

    public String toData() {
        return String.format("D | %s | %s | %s", this.getStatusIcon(), this.getDescription(), this.doBy);
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + this.doBy + ")";
    }
}
