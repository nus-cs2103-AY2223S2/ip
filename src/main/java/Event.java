public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description) {
        super(description.split("/")[0]);
        this.start = description.split("/")[1].substring(5);
        this.end = description.split("/")[2].substring(3);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.start + "to: " + end + ")";
    }

}
