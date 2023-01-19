package tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String[] args){
        super(args[0]);
        this.from = args[1];
        this.to = args[2];
    }

    @Override
    public String toString(){
        return "[E]"
                + super.toString()
                + " (from: " + this.from + ", to: "
                + this.to + ")";
    }
}
