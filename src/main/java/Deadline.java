public class Deadline extends Task {
    private String desc;
    private String dueDate;

    public Deadline(int id, String description, String dueDate) {
        super(id);
        desc = description;
        this.dueDate = dueDate;
    }

    public String description() {
        return desc;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", description(), dueDate);
    }
}
