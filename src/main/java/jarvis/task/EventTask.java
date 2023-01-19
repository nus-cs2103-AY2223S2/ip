package jarvis.task;

import jarvis.exception.CommandParseException;
import jarvis.exception.InvalidParameterException;
import jarvis.exception.MissingParameterException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private final LocalDate fromDate;
    private final LocalDate toDate;

    /**
     * Constructor of an event task, marked as undone.
     * @param description Description of the task.
     * @param fromDate Date string when the event starts.
     * @param toDate Date string when the event ends.
     * @throws CommandParseException If description, fromDate or toDate is invalid.
     */
    public EventTask(
            String description,
            String fromDate,
            String toDate
    ) throws CommandParseException {
        this(description, fromDate, toDate, false);
    }

    /**
     * Constructor of an event task.
     * @param description Description of the task.
     * @param fromDate Date string when the event starts.
     * @param toDate Date string when the event ends.
     * @param isDone Whether the event is to be marked as done.
     * @throws CommandParseException If description, fromDate or toDate is invalid.
     */
    public EventTask(String description, String fromDate, String toDate, boolean isDone) throws CommandParseException {
        super(description, isDone);
        if (fromDate == null || toDate == null || fromDate.isBlank() || toDate.isBlank()) {
            throw new MissingParameterException(
                    "Missing fromDateTime or toDateTime",
                    "Start ('/from ...') and end ('/to ...') markers are needed."
            );
        }
        try {
            this.fromDate = LocalDate.parse(fromDate);
        } catch (DateTimeParseException e) {
            throw new InvalidParameterException(
                    "Invalid fromDate",
                    "I don't understand the date provided after /from."
            );
        }
        try {
            this.toDate = LocalDate.parse(toDate);
        } catch (DateTimeParseException e) {
            throw new InvalidParameterException(
                    "Invalid toDate",
                    "I don't understand the date provided after /to."
            );
        }

        if (this.fromDate.isAfter(this.toDate)) {
            throw new InvalidParameterException(
                    "fromDate should be before toDate",
                    "The start marker ('/from ...') should be earlier than the end marker ('/to ...')."
            );
        }
    }

    @Override
    public boolean satisfies(TaskFilter filter) {
        if (filter == null || filter.isEmpty()) {
            return true;
        }
        boolean isBefore = filter.getBeforeDate() == null || !filter.getBeforeDate().isBefore(this.fromDate);
        boolean isAfter = filter.getAfterDate() == null || !filter.getAfterDate().isAfter(this.toDate);
        return isBefore && isAfter;
    }

    @Override
    public String serialize() {
        String[] data = {
                "E",
                String.valueOf(this.isDone()),
                this.getDescription(),
                String.valueOf(this.fromDate),
                String.valueOf(this.toDate)
        };
        return String.join(" / ", data);
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s, to: %s)",
                super.toString(),
                this.fromDate,
                this.toDate
        );
    }
}
