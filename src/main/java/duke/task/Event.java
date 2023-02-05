package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    /** Start date and time of event */
    private LocalDateTime startDateTime;
    /** End date and time of event */
    private LocalDateTime endDateTime;

    /**
     * Constructs Event class.
     *
     * @param description Description of task.
     * @param start Start date and time of task.
     * @param end End date and time of task.
     * @throws DukeException If dates entered are invalid.
     */
    public Event(String description, String start, String end) throws DukeException {
        super(description);
        LocalDate startDate = null;
        LocalDateTime startDateTime = null;
        LocalDate endDate = null;
        LocalDateTime endDateTime = null;
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
            // Goes through list of formatters to see which matches the start date input
            try {
                startDateTime = LocalDateTime.parse(start, formatter);
                break;
            } catch (DateTimeParseException e) {
                try {
                    startDate = LocalDate.parse(start, formatter);
                    break;
                } catch (DateTimeParseException e2) {
                    // Invalid format, try the next one
                }
            }
        }
        for (DateTimeFormatter formatter : formatters) {
            // Goes through list of formatters to see which matches the end date input
            try {
                endDateTime = LocalDateTime.parse(end, formatter);
                break;
            } catch (DateTimeParseException e) {
                try {
                    endDate = LocalDate.parse(end, formatter);
                    break;
                } catch (DateTimeParseException e2) {
                    // Invalid format, try the next one
                }
            }
        }

        // If date or dateTime is still null, input is in invalid format
        if ((startDate == null && startDateTime == null) || (endDate == null && endDateTime == null)) {
            throw new DukeException("Reenter dates in this format: (ddMMyyyy) or (ddMMyyyy HHmm).");
        }

        // Converts start and end date to include time
        if (startDate != null) {
            this.startDateTime = startDate.atStartOfDay();
        } else {
            this.startDateTime = startDateTime;
        }
        if (endDate != null) {
            this.endDateTime = endDate.atStartOfDay().plusDays(1).minusNanos(1);
        } else {
            this.endDateTime = endDateTime;
        }

        // Checks if start date is before end date
        if (this.endDateTime.isBefore(this.startDateTime)) {
            throw new DukeException("End date cannot be before start date.");
        }
    }

    /**
     * Gets start date and time of task.
     *
     * @return Start date and time of task.
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Gets end date and time of task.
     *
     * @return End date and time of task.
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Checks if event is upcoming.
     *
     * @return Status of event whether it is upcoming.
     */
    public boolean isUpcoming() {
        return LocalDateTime.now().isBefore(startDateTime);
    }

    /**
     * Checks if event is ongoing.
     *
     * @return Status of event whether it is ongoing.
     */
    public boolean isOngoing() {
        return LocalDateTime.now().isAfter(startDateTime) && LocalDateTime.now().isBefore(endDateTime);
    }

    /**
     * Checks if event has passed.
     *
     * @return Status of event whether it has passed.
     */
    public boolean isPassed() {
        return LocalDateTime.now().isAfter(endDateTime);
    }

    /**
     * {@inheritDoc}
     *
     * Includes type of task and its start and end dates.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String start = this.startDateTime.format(formatter);
        String end = this.endDateTime.format(formatter);
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
