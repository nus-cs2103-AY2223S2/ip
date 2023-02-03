package duke.tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event object that extends Task.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for a Event object.
     *
     * @param description The Event description.
     * @param from The start date of the Event.
     * @param to The end date of the Event.
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter newDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        DateTimeFormatter newDate = DateTimeFormatter.ofPattern("MMM dd yyyy");

        try {
            LocalDateTime fromOutput = LocalDateTime.parse(from, dateTimeFormatter);
            this.from = fromOutput.format(newDateTime);
        } catch (DateTimeParseException e) {
            LocalDate fromOutput = LocalDate.parse(from, dateFormatter);
            this.from = fromOutput.format(newDate);
        }
        try {
            LocalDateTime toOutput = LocalDateTime.parse(to, dateTimeFormatter);
            this.to = toOutput.format(newDateTime);
        } catch (DateTimeParseException e) {
            LocalDate toOutput = LocalDate.parse(to, dateFormatter);
            this.to = toOutput.format(newDate);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
