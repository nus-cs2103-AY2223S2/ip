package duke.workflow;

public abstract class Event {
    boolean isFinalEvent;

    public Event (boolean isFinalEvent) {
        this.isFinalEvent = isFinalEvent;
    }
    public abstract Event toNext();

    public boolean getStatus() {
        return this.isFinalEvent;
    }
}