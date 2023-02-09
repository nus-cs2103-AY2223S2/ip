package Willy.task;

/**
 * Represents the Event
 */
public class Event extends Task {
    private String from;
    private String to;
    private String code;

    /**
     * Creates the Event
     * @param msg
     * @param from
     * @param to
     */
    public Event(String msg, String from, String to) {
        super(msg);
        this.from = from;
        this.to = to;
        this.code = "[E]";
    }
    
    /** 
     * @return String
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Returns a string representation of the Event
     */
    @Override
    public String toString() {
        return code + super.toString() + "(" + from + to + ")";
    }

}
