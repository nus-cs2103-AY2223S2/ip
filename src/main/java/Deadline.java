import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String taskType = "[D]";
    private LocalDate date;

    public Deadline(String description, LocalDate date) throws MissingDescriptionException {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM u");
        String dateString = date.format(dateFormatter);
        return taskType + super.toString() + " (by: " + dateString + ")";
    }

    @Override
    public String toStorageData() {
        String completed = this.getStatusIcon();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM u");
        String dateString = date.format(dateFormatter);
        return taskType + "//" + completed + "//" + description + "//" + dateString;
    }
}