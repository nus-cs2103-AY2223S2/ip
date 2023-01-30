package DukeHelpfulCode;

public class Event extends Task{
    // tasks that start at a specific date/time and ends at a specific date/time

    private String startDateTime;
    private String endDateTime;

    public Event(String name, String startDateTime, String endDateTime) {
        super(name);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.startDateTime + " to: " + this.endDateTime + ")";
    }

}
