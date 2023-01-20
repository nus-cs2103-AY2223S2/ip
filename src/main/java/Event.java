public class Event extends Task {
    private String fromTime;
    private String toTime;
    
    Event(String desc, String fromTime, String toTime) {
        super(desc);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
    
    @Override
    public String toString() {
        return String.format("[E][%s]  %s  (%s - %s)",
                getStatus(), this.desc, this.fromTime, this.toTime);
    }
}