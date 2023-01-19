/**
 * A task subclass to represent a Deadline (due date).
 */
public class Deadline extends Task{
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String getDescription() {
        return this.description + " (by: " + this.dueDate + ")";
    }
}
