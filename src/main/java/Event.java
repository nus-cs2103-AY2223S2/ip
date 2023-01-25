public class Event extends Task {
    private String from;
    private String to;

    Event(String desc, boolean done, String from, String to) {
        super(desc, done);
        this.from = from;
        this.to = to;
    }

    String getFrom(){
        return this.from;
    }

    String getTo(){
        return this.to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + this.from + " to " + this.to + ")";
    }
}
