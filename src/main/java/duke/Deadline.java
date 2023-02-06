package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a type of task that has a "by" date.
 */
public class Deadline extends Task {

    private LocalDateTime byDate;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy H:mm");

    public Deadline(String description, String by) {
        super(description);
        byDate = LocalDateTime.parse(by, FORMATTER);
    }

    @Override
    public String getIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return description + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    /**
     * Gets the "by" date String
     *
     * @return String of "by" date
     */
    public String getByDate() {
        return byDate.format(FORMATTER);
    }

}
