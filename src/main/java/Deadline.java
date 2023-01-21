public class Deadline extends Task {
    private static final String taskType = "[D]";
    private String date;

    public Deadline(String description, String date) throws MissingDescriptionException {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return taskType + super.toString() + " (by: " + date + ")";
    }
}
