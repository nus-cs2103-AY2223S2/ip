abstract class Event {
    boolean isFinalEvent;

    public Event (boolean isFinalEvent) {
        this.isFinalEvent = isFinalEvent;
    }
    abstract Event toNext();

    boolean getStatus() {
        return this.isFinalEvent;
    }
}