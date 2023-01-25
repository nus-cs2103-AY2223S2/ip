public class Event extends Task{
    protected String from, to;
    protected String type = "[E]";
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static void main(String[] args) {
        Event a = new Event("read book", "Mon 2pm" , "4pm");
        System.out.println(a);
    }
    @Override
    public String toString() {
        return type + super.toString() + " (from: " + from + " to: " + to + ")";
    }

}
