public class Events extends Task {
    private String from;
    private String to;

    public Events(String eventName, String from, String to){
        super(eventName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(" + from + to + ")";
    }
}
