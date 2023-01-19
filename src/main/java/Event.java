public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " "
                + super.getDescription() + "(from: " + this.from + "to: " + this.to + ")";
    }

    public static Event addEvent(String details) {
        String description = details.substring(0, details.indexOf("/from"));
        String fromAndTo = details.substring(details.indexOf("/from") + "/from".length() + 1);
        String from = fromAndTo.substring(0, fromAndTo.indexOf("/to"));
        String to = fromAndTo.substring(fromAndTo.indexOf("/to") + "/to".length() + 1);
        Event t = new Event(description, from, to);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        return t;
    }
}