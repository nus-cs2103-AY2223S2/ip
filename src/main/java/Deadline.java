public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String printTask() {
        return String.format("[D]%s (by: %s)", super.printTask(), this.by);
    }

    @Override
    public String formatForFile() {
        return String.format("%s|%s|%s", "D", super.formatForFile(), this.by);
    }

}
