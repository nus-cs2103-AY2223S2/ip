abstract class Event {
    boolean isFinalEvent;

    public Event (boolean isFinalEvent) {
        this.isFinalEvent = isFinalEvent;
    }
    abstract Event toNext();

    abstract TaskList getTaskList();

    boolean getStatus() {
        return this.isFinalEvent;
    }
}