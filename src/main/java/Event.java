public class Event extends Task{
    String from;
    String to;

    public Event(String desc,String from, String to) {
        super(desc,"E");
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return super.toString() + String.format("[from: %sto: %s]", this.from, this.to);
    }
}
