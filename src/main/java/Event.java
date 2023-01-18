public class Event extends Task {
    private String from;
    private String to;

    public Event(String desc, String from, String to) {
        super(desc);
        setFrom(from);
        setTo(to);
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    private String duration() {
        return "(from: " + this.from + " to: "
                + this.to + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + this.duration();
    }
    
}
