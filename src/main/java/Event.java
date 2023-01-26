public class Event extends Tasks {
    private String from = "";
    private String to = "";
    public Event(String content, boolean isDone, String from, String to) {
        super(content, isDone);
        this.from = from;
        this.to = to;
        this.type = 'E';
    }
//bugfix?
    //second commit to feature1
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
