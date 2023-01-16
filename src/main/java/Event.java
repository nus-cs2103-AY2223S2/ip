public class Event extends Task{
    private String to;
    private String from;

    public Event(String description, String from, String to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString(){
        return "[E]" + this.getStatusIcon() + " " + this.description + " (from: "+ from + " to: " + to + ")";
    }
}
