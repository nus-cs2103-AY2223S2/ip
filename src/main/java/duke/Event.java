package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * This is a class for Event action
 */

public class Event extends Task {
    protected String from;
    protected String to;
    protected String date;
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * This is a constructor for event class
     * @param description description of event
     * @param date date during which the event happens
     */
    public Event(String description, String date)  {
        super(description);
        String[] fromAndTo = date.split("/");
        this.from =  fromAndTo[0];
        this.from = from.replaceAll("from ", "");
        this.from = this.from.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.start = LocalDateTime.parse(this.from, formatter);
        this.date = date;
        this.to = fromAndTo[1];
        this.to = to.replaceAll("to","");
        this.end = LocalDateTime.parse(this.to, formatter);
        Task.actions += 1;

    }

    /**
     * Returns a string representation of the Event object
     * @return String string representation of the Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to + ")";
    }

    /**
     * Returns a string representation of the Event object in the list
     * @return a string representation of the Event object in the list
     */
    @Override
    public String toSaveString() {
        String timeStr = this.start.format(DateTimeFormatter.ofPattern("HH:mm, MMM dd yyyy"));
        String timeEnd = this.end.format(DateTimeFormatter.ofPattern("HH:mm, MMM dd yyyy"));
        String date = "" + timeStr + "-" + timeEnd;
        return String.format("event || %s || %s || %s", super.toSaveString(), this.description, date);
    }

    /**
     * Print string representation when event is added to list
     */
    public void toPrintEventString() {
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
        System.out.println("Now you have " + Task.actions + " tasks in the list");
    }

    /**
     * Adds event into tasklist
     */
    public void handleEvent() {
        this.toPrintEventString();
        Task.tasks.add(this);
    }
}
