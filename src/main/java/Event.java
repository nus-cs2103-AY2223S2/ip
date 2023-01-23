import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

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

        if (this.endDateTime.isBefore(this.startDateTime)) {
            throw new DukeException("End date cannot be before start date.");
        }
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public boolean isUpcoming() {
        return LocalDateTime.now().isBefore(startDateTime);
    }

    public boolean isOngoing() {
        return LocalDateTime.now().isAfter(startDateTime) && LocalDateTime.now().isBefore(endDateTime);
    }

    public boolean isPassed() {
        return LocalDateTime.now().isAfter(endDateTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String start = this.startDateTime.format(formatter);
        String end = this.endDateTime.format(formatter);
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
