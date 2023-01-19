public class Event extends Task{
    private String from;
    private String to;
    private String code;

    public Event(String msg, String from, String to) {
        super(msg);
        this.from = from;
        this.to = to;
        this.code = "[E]";
    }

    public String getCode(){
        return this.code;
    }

    @Override
    public String toString() {
        return code + super.toString() + " (" + from + to + ")";
    }
    
}

