import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Event extends Task{
    protected Date eventStart;
    protected Date eventEnd;

    public Event(String item, String type, Date start, Date end) {
        super(item,type);
        eventStart = start;
        eventEnd = end;
    }
    @Override
    public String toString() {
        SimpleDateFormat convertToString = new SimpleDateFormat("dd MMM yyyy HH:mm a", Locale.ENGLISH);
        return "[E]" + super.toString() + " (From: " + convertToString.format(eventStart) + " To: " + convertToString.format(eventEnd) + ")";
    }
}
