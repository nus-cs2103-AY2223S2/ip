public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return this.isMarked
                ? String.format("[D][X] %1$s (by: %2$s)", this.description, this.dueDate)
                : String.format("[D][ ] %1$s (by: %2$s)", this.description, this.dueDate);
    }
}
