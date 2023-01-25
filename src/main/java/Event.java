public class Event extends Task{

    protected String from;
    protected String to;
    protected String desc;

    public Event(String desc, String from, String to) {
        super(desc);
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from +" to: " + to +")";
    }

    public String asCSV() {
        return "E," + desc + "," + from + "," + to;
    }
}
