import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Event extends Task{
    protected Date eventStart;
    protected Date eventEnd;

    protected String startString;
    protected String endString;

    public Event(String item, String type, Date start, Date end, String startStringVer, String endStringVer) {
        super(item,type);
        eventStart = start;
        eventEnd = end;
        startString = startStringVer;
        endString = endStringVer;
    }
    @Override
    public String toString() {
        SimpleDateFormat convertToString = new SimpleDateFormat("dd MMM yyyy HH:mm a", Locale.ENGLISH);
        return "[E]" + super.toString() + " (From: " + convertToString.format(eventStart) + " To: " + convertToString.format(eventEnd) + ")";
    }

    @Override
    public String getTime() {
        return startString + "-" + endString;
    }
}
