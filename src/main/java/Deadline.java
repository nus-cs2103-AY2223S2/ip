public class Deadline extends Task {

    // Attributes:
    protected String by_date;

    // Constructor:
    public Deadline(String title, String by_date) {
        super(title);
        this.by_date = by_date;
    }

    // Methods:
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by_date + ")";
    }
}
