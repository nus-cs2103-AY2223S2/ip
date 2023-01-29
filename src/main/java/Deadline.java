public class Deadline extends Task {
    protected String deadline;
    public Deadline(String description, String deadline) {
        super(description, "D");
        this.deadline = deadline;
    }
    public String toString() {
        return String.format("%s | by: %s", this.description, this.deadline);
    }
}
