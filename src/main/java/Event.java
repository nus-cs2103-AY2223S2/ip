public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    String getFrom() {
        return from;
    }

    void setFrom(String from) {
        this.from = from;
    }

    String getTo() {
        return to;
    }

    void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toCsv() {
        return "E," + super.toCsv() + ","
                + from + "," + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (From: " + from + " To: " + to + ")";
    }
}
