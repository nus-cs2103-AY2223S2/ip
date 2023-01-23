public class Event extends Task {
    private String fromTime;
    private String toTime;
    
    Event(String desc, boolean isDone, String fromTime, String toTime) {
        super(desc, isDone);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
    
    Event(String desc, String fromTime, String toTime) {
        super(desc);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
    
    @Override
    String getSymbol() {
        return "E";
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s - %s)",
                super.toString(), this.fromTime, this.toTime);
    }
    
    @Override
    String makeFileFriendly() {
        return String.format("%s%s%s%s%s",
                super.makeFileFriendly(), SEP, this.fromTime, SEP, this.toTime);
    }
}