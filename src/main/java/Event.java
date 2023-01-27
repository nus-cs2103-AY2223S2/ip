/**
 * The Event class is a type of task.
 *
 * @author Chia Jeremy
 */

public class Event extends Task {

    protected String startDT;
    protected String endDT;

    public Event(String description, String startDT, String endDT) {
        super(description);
        this.startDT = startDT;
        this.endDT = endDT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDT + " to: " + endDT + ")";
    }
}
