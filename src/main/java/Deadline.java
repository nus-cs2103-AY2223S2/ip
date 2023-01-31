import java.time.LocalDate;

public class Deadline extends Task {
    private static final String taskType = "[D]";
    private LocalDate date;

    public Deadline(String description, LocalDate date) throws MissingDescriptionException {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        String dateString = date.getDayOfMonth() + " " + date.getMonth().toString() + " " + date.getYear();
        return taskType + super.toString() + " (by: " + dateString + ")";
    }
}
