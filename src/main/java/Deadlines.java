public class Deadlines extends Task {
    protected String dueDate;
    public Deadlines(String name, String dueDate) {
        super(name, "D");
        this.dueDate = dueDate;
    }

    @Override
    public String description() {
        return String.format("%s (by: %s)", super.description(), this.dueDate);
    }
}
