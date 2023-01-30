package Duke.Tasks;

public class Deadline extends Task {

    protected String dueDate;
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String printTask() {
        return String.format("D | %d | %s | %s ", isComplete ? 1 : 0, description, dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
