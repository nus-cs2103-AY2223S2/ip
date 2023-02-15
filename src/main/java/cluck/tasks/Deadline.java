package cluck.tasks;

public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public Deadline(boolean isMarked, String description, String dueDate) {
        super(isMarked, description);
        this.dueDate = dueDate;
    }

    @Override
    public String makeSaveFormat() {
        return String.format("D|%1$s|%2$s\n", (this.isMarked ? "1" : "0"), this.description
                + "|" + this.dueDate);
    }

    @Override
    public String toString() {
        return this.isMarked
                ? String.format("[D][X] %1$s (by: %2$s)", this.description, this.dueDate)
                : String.format("[D][ ] %1$s (by: %2$s)", this.description, this.dueDate);
    }
}
