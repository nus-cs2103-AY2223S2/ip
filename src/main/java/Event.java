public class Event extends Task{
    String from;
    String to;

    Event(String name) {
        super(name.split("/")[0].substring(6));
        this.from = name.split("/")[1].substring(5);
        this.to = name.split("/")[2].substring(3);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
}
