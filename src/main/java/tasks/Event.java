package tasks;

public class Event extends Task {
    protected String from;
    protected String to;
    private static final String PREFIX = "E";

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public String save() {
        StringBuilder response = new StringBuilder("");
        response.append(getPrefix() + ",");
        response.append(description + ",");
        response.append(from + ",");
        response.append(to + ",");
        response.append(isDone + "\n");
        return response.toString();
    }

    @Override
    public String toString(){
        return super.toString()
                + " (from: " + this.from + ", to: "
                + this.to + ")";
    }
}
