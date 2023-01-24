public class Event extends Task {
    private String from;
    private String to;

    private Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public static Event createEvent(String desc) {
        String[] eventArr = desc.split("/", 3);
        String eventDesc = eventArr[0].trim();
        String from = eventArr[1].substring(5).trim();
        String to = eventArr[2].substring(3);
        return new Event(eventDesc, from, to);
    }
}
