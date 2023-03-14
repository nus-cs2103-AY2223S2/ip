package duke.tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Ui;



/**
 * The Deadline class that extends Task.
 */
public class Deadline extends Task {
    private String by;
    /**
     * Constructor for a Deadline object.
     *
     * @param description The Event description.
     * @param date The date of the Deadline.
     */
    public Deadline(String description, String date) throws DukeException {
        super(description);
        LocalDateTime byDateTime;

        try {
            byDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.by = byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            LocalDate byDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.by = byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            byDateTime = byDate.atTime(23, 59);
        }
        if (!byDateTime.isAfter(LocalDateTime.now())) {
            throw new DukeException(Ui.datePassed());
        }
    }

    /**
     * Check if the deadline's date contains the given keyword.
     *
     * @param keyword The keyword argument.
     * @return A boolean value.
     */
    public boolean dateContains(String keyword) {
        if (this.by.length() >= keyword.length()) {
            return this.by.toLowerCase().contains(keyword.toLowerCase());
        } else {
            return false;
        }
    }

    /**
     * Updates the deadline attribute of the object.
     *
     * @param date String input of the new date.
     * @throws DukeException
     */
    public void updateDeadline(String date) throws DukeException {
        LocalDateTime newByDateTime;

        try {
            newByDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.by = newByDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            LocalDate byDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.by = byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            newByDateTime = byDate.atTime(23, 59);
        }
        if (!newByDateTime.isAfter(LocalDateTime.now())) {
            throw new DukeException(Ui.datePassed());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "\n (by: " + this.by + ")\n";
    }
}
