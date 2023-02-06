package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String TASK_TYPE = "[D]";
    private LocalDate date;

    public Deadline(String description, LocalDate date) throws MissingDescriptionException {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM u");
        String dateString = date.format(dateFormatter);
        return TASK_TYPE + super.toString() + " (by: " + dateString + ")";
    }

    @Override
    public String toStorageData() {
        String completionStatus = this.getStatusIcon();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM u");
        String dateString = date.format(dateFormatter);
        return TASK_TYPE + "//" + completionStatus + "//" + description + "//" + dateString;
    }
}