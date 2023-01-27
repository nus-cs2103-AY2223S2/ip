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

    public String getStartDT() {
        return this.startDT;
    }

    public String getEndDT() {
        return this.endDT;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (from: " + startDT + " to: " + endDT + ")";
    }
}
