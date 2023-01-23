public class Deadline extends Task {
    private String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }

    @Override
    public String toFormatString() {
        return "D | " + (super.done ? "1" : "0") + " | " + super.name + " | " + dueDate;
    }
}
