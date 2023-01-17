public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to)  {
        super(description);
        from = from.replaceAll("by", "");
        from = from.replaceAll("/","");
        from = from.replaceAll("from","");
        this.from = from;
        to = to.replaceAll("to", "");
        this.to = to;
        Task.actions += 1;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + "to:" + to + ")";
    }


}
