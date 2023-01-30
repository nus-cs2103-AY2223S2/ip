package tasks;
public class Events extends Task {

    protected String from;
    protected String to;
    private static final String tag = "E";

    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String saveTask() {
        String completed = this.isDone? "1":"0";
        return this.tag + " | " + completed + " | "
                + this.description + " | " + this.from + "-" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + from + " to:" + to + ")" +"\n";
    }
}