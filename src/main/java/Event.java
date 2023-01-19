public class Event extends Tasks {
    private String from = "";
    private String to = "";
    public Event(String content, String from, String to) {
        super(content);
        this.from = from;
        this.to = to;
        this.type = 'E';
    }

    @Override
    public String getDuration() {
        return this.from + " " + this.to;
    }

    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "]"
                + "[" + this.getStatusIcon() + "] " + this.seeTaskContent() + "(from: " + this.from + "to: " + this.to + ")";
    }
}
