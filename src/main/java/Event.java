import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task{

    protected String start;
    protected String end;

    protected LocalDate date;
    protected LocalTime time = null;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public void handleDateAndTime() {
        if (end.contains(" ")) {
            LocalDate newDate = LocalDate.parse(end.split(" ")[0]);
            LocalTime newTime = LocalTime.parse(end.split(" ")[1]);
            this.date = newDate;
            this.time = newTime;
        }
        else {
            LocalDate newDate = LocalDate.parse(end);
            this.date = newDate;
        }
    }

    public String printDateAndTime() {
        if (time.equals(null)) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        else {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + Integer.toString(time.getHour()) + Integer.toString(time.getMinute());
        }
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ") ";
    }
}
