public class Event extends Task{

    protected String startTime;
    protected String endTime;

    public static Event create(String str) {
        String[] text = str.split(" /");
        String desc = text[0];
        String from = text[1].substring(5);
        String to = text[2].substring(3);
        return new Event(desc, from, to);
    }

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }



    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
