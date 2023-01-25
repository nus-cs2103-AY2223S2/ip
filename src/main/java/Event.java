import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task{
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private Date eventStartTime;
    private Date eventEndTime;

    public Event(String description, LocalDate eventStartDate, Date eventStartTime, LocalDate eventEndDate, Date eventEndTime){
        super(description);
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    @Override
    public String toString() {

        String formattedStartDate = eventStartDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedEndDate = eventEndDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedStartTime = new SimpleDateFormat("h:mm a").format(eventStartTime);
        String formattedEndTime = new SimpleDateFormat("h:mm a").format(eventEndTime);
        String eventStartCombined = formattedStartDate + " " + formattedStartTime;
        String eventEndCombined = formattedEndDate + " " + formattedEndTime;
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (from: " + eventStartCombined + " to: " + eventEndCombined +")";
    }
}
