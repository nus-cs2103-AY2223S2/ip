public class Event extends Task{
    protected String eventStart;
    protected String eventEnd;

    public Event(String item, String type, String time1, String time2) {
        super(item,type);
        String x1[] = time1.split(" ", 2);
        String x2[] = time2.split(" ", 2);
        eventStart = x1[1];
        eventEnd = x2[1];
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + eventStart + " to: " + eventEnd + ")";
    }
}
