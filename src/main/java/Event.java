public class Event extends Task{
    protected String from;
    protected String to;
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        String mark = this.isDone()? "1": "0";
        return String.format("E | %s | %s | %s | %s", mark, this.getDescription(), this.from, this.to);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.from + " to:" + this.to + ")";
    }
}
