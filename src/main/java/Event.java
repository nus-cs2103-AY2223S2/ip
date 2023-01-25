import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Event extends Task {

    private LocalDate from;
    private LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = processDate(from);
        this.to = processDate(to);
    }

    private LocalDate processDate(String deadline) {
        // now assume date is in the form
        // dd/mm/yy or yy-mm-dd
        // in later versions, more form of date and time will be resolved
        LocalDate dateTime;
        try {
            dateTime = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            String[] parts = deadline.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            dateTime = LocalDate.of(year, month, day);
        }
        return dateTime;
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    private String deadlineFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    private String getEventInterval() {
        return " (from: " + deadlineFormat(from) + " to: " + deadlineFormat(to) + ")";
    }

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + getCurrentDescription() + this.getEventInterval();
    }
}
