public class Event extends Task{
    String from;
    String to;

    Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String storageFormat() {
        return String.join("|", "E", super.storageFormat(), from, to) + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
}
