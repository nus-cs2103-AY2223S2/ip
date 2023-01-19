/**
 * A task subclass to represent a Deadline (due date).
 */
public class Deadline extends Task{
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }
}
