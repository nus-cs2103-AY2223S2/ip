public class Deadline extends Task {
    protected String endDate;

    Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Deadline/" + getTaskDescription() + "(by " + endDate + ")";
    }
}
