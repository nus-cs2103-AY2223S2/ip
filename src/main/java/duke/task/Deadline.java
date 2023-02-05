package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    /** Date of deadline */
    private LocalDateTime deadline;

    /**
     * Constructs Deadline class.
     *
     * @param description Description of task.
     * @param deadline Deadline of task.
     * @throws DukeException If date entered is invalid.
     */
    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        LocalDate date = null;
        LocalDateTime dateTime = null;
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("ddMMyyyy HHmm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("ddMMyyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        };

        for (DateTimeFormatter formatter : formatters) {
            // Goes through list of formatters to see which matches the deadline date input
            try {
                dateTime = LocalDateTime.parse(deadline, formatter);
                break;
            } catch (DateTimeParseException e) {
                try {
                    date = LocalDate.parse(deadline, formatter);
                    break;
                } catch (DateTimeParseException e2) {
                    // Invalid format, try the next one
                }
            }
        }
        // If date or dateTime is still null, input is in invalid format
        if (date == null && dateTime == null) {
            throw new DukeException("Reenter date in this format: (ddMMyyyy) or (ddMMyyyy HHmm).");
        }

        // Converts deadline date to include time
        if (date != null) {
            this.deadline = date.atStartOfDay().plusDays(1).minusNanos(1);
        } else {
            this.deadline = dateTime;
        }
    }

    /**
     * Gets deadline of task.
     *
     * @return Deadline of task.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Sets deadline of task.
     *
     * @param deadline Deadline of task to be set to.
     */
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    /**
     * Checks if deadline is overdue.
     *
     * @return Status of event whether it is overdue.
     */
    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(deadline);
    }

    /**
     * Checks if deadline is upcoming.
     *
     * @return Status of event whether it is upcoming.
     */
    public boolean isUpcoming() {
        return LocalDateTime.now().isBefore(deadline);
    }

    /**
     * {@inheritDoc}
     *
     * Includes type of task and its deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String deadline = this.deadline.format(formatter);
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
