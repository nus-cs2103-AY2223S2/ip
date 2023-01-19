public class Event extends Task {
    protected String to;
    protected String from;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj instanceof Event) {
            Event x = (Event) obj;
            if(this.name.equals(x.name)) {
                return true;
            }
        }
        return false;
    }
}
