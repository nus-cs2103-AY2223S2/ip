public class Deadline extends Task {

    private final String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + dueDate + ")";
    }

    @Override
    public String getTaskState() {
        return "D | " + super.getTaskString() +
                " | " + this.dueDate;
    }
}
