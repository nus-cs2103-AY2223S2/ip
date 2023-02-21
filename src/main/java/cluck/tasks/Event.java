package cluck.tasks;

import java.time.LocalDateTime;

/**
 * Event is a Task with a start date-time and end date-time.
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Instantiates a new Event.
     *
     * @param description event description
     * @param startTime   start date-time of event
     * @param endTime     end date-time of event
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Instantiates a new Event.
     *
     * @param isMarked    indicates whether event is marked
     * @param description event description
     * @param startTime   when the event starts
     * @param endTime     when the event ends
     */
    public Event(boolean isMarked, String description, String startTime, String endTime) {
        super(isMarked, description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String makeSaveFormat() {
        return "E" + super.makeSaveFormat() + "|" + this.startTime + "|" + this.endTime + "\n";
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() +
                String.format(" (from: %1$s, to: %2$s)", this.startTime, this.endTime);

    }
}
