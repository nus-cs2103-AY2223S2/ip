import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task{

    protected String eventDetail;

    protected LocalDate time;

    protected String timeDescription;

    /**
     * Constructor
     * @param description the entire description including task name and time of the task
     */
    public Event(String description) {
        super(description);
        String detail = description.split("/")[0].split(" ", 2)[1];
        this.eventDetail = detail;
        String timeDescription = description.split("/")[1].split(" ")[0] + ": " + description.split("/")[1].split(" ",2)[1] +
                description.split("/")[2].split(" ")[0] + ": " + description.split("/")[2].split(" ",2)[1];
        this.timeDescription = timeDescription;
        this.time = LocalDate.parse(timeDescription.split(": ",2)[1].split(":", 2)[0] + timeDescription.split(":",2)[1].split(":", 2)[1]);
    }

    /**
     * String representation of the task details
     * @return a string including the task name and duration
     */
    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + eventDetail + "(" + timeDescription +  ")";
    }

    /**
     * Get the type of task
     * @return a string "event"
     */
    public String getType() {
        return "E";
    }

    /**
     * Gets the type of task
     * @return a String representing event detail
     */
    public String getDetail() {
        return this.eventDetail;
    }

    /**
     * Gets the time of task
     * @return a String representing time
     */
    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
