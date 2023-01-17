public class Event extends Task{
    String from;
    String to;
    public Event(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
        this.type = "[E]";
    }

    public Event(String title, String from, String to, boolean done) {
        super(title);
        this.from = from;
        this.to = to;
        this.type = "[E]";
        this.done = done;
    }

    @Override
    public String toData() {
        return this.type + " | " + (this.done ? "1" : "0") + " | " + this.title + " | " + this.from + "-" + this.to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
