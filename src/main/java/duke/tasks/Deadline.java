package duke.tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import duke.dukeexceptions.DukeExceptions;

/**
 * A task representing a deadline.
 */
public class Deadline extends Task {

    private static final String tag = "D";
    protected LocalDateTime by;

    /**
     * Constructor for a deadline.
     *
     * @param description the description of the deadline
     * @param by the date and time of the due date
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            LocalDate byLocalDate = LocalDate.parse(by.split(" ")[0]);
            LocalTime byLocalTime = LocalTime.parse(by.split(" ")[1]);

            this.by = LocalDateTime.of(byLocalDate, byLocalTime);
        } catch (DateTimeParseException e) {
            throw new DukeExceptions("Format of date was not recognized, use YYYY-MM-DD and HH:MM");
        }
    }

    /**
     * Returns the deadline in a formatted string to be saved locally.
     *
     * @return the deadline in string format
     */
    public String saveTask() {
        String completed = this.isDone ? "1" : "0";
        String formattedDate = by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return this.tag + " | " + completed + " | "
                + this.description + " | " + formattedDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by:" + by.format(DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a")) + ")"
                + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Deadline)) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        Deadline deadline = (Deadline) o;
        return Objects.equals(by, deadline.by);
    }

}
