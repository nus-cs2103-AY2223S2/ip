public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String desc) {

        super(desc.substring(0, desc.indexOf("/from") - 1));
        this.from = desc.substring(desc.indexOf("/from") + 6, desc.indexOf("/to") - 1);
        this.to = desc.substring(desc.indexOf("/to") + 4);

    }

    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", from, to);
    }

}