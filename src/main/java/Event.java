import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = getStartDateTime(from);
        this.to = getEndDateTime(to);
    }

    public LocalDateTime getStartDateTime(String from) {
        LocalDateTime startDateTime = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            startDateTime = LocalDateTime.parse(from, dateTimeFormatter);
            return startDateTime;
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("\t Wrong Wrong Date Format!\n");
        }
        return startDateTime;
    }

    public LocalDateTime getEndDateTime(String from) {
        LocalDateTime endDateTime = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            endDateTime = LocalDateTime.parse(from, dateTimeFormatter);
            return endDateTime;
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("\t Wrong Wrong Date Format!\n");
        }
        return endDateTime;
    }

    public String startDateTime() {
        String startDateTime = null;
        String startDayOfWeek = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        try {
            startDateTime = this.from.format(format);
            startDayOfWeek = this.from.getDayOfWeek().toString();
            return startDayOfWeek + ", " + startDateTime;
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("\t Wrong Wrong Date Format!\n");
        }

        String endDateTime = this.to.format(format);
        String endDayOfWeek = this.to.getDayOfWeek().toString();
        return startDayOfWeek + ", " + startDateTime;
    }

    public String endDateTime() {
        String endDateTime = null;
        String endDayOfWeek = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        try {
            endDateTime = this.to.format(format);
            endDayOfWeek = this.to.getDayOfWeek().toString();
            return endDayOfWeek + ", " + endDateTime;
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("\t Wrong Wrong Date Format!\n");
        }
        return endDayOfWeek + ", " + endDateTime;
    }

    public String getFrom() {
        return startDateTime();
    }

    public String getTo() {
        return endDateTime();
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (from: " + startDateTime()
                + " to: " +  endDateTime() + ")";
    }
}
