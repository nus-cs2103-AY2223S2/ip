public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public String getEnd() {
        return end;
    }

    public String getStart() {
        return start;
    }

    @Override
    public String toString() {
        String box;
        if (this.getDone()) {
            box = "[X]";
        } else {
            box = "[ ]";
        }
        return"[E]" + box + this.getName() + "(from: " + this.getStart() + "to " + this.getEnd() + ")";
    }
}
