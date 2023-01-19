public class Event extends Task {
    String from;
    String to;

    Event(String desc, boolean done, String from, String to) {
        super(desc, done);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + this.from + " to " + this.to + ")";
    }
}
