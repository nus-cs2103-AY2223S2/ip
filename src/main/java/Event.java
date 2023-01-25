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

    public Event(String desc, String from, String to, boolean b) {
        super(desc, b);
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from +" to: " + to +")";
    }

    public String asCSV() {
        if (super.isDone) {
            return "E,1," + desc + "," + from + "," + to;
        } else {
            return "E,0," + desc + "," + from + "," + to;
        }
    }
}
