public class Event extends Item {
    private static final String TYPE = "[E]";
    private String start;
    private String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String messageWhenAdded() {
        return "DukeyList just added a new event:";
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X] " + this.getName() + " (" + this.start + " to " + this.end + ")";
        }
        return TYPE + "[ ] " + this.getName() + " (" + this.start + " to " + this.end + ")";
    }



}
