import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
    private static final DateTimeFormatter FORM = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    
    Event(String desc, boolean isDone, LocalDateTime fromTime, LocalDateTime toTime) {
        super(desc, isDone);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
    
    Event(String desc, LocalDateTime fromTime, LocalDateTime toTime) {
        super(desc);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
    
    @Override
    String getSymbol() {
        return "E";
    }
    
    @Override
    boolean hasTime() {
        return true;
    }
    
    @Override
    LocalDateTime getTime() {
        return this.fromTime;
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s - %s)",
                super.toString(), this.fromTime.format(FORM), this.toTime.format(FORM));
    }
    
    @Override
    String makeFileFriendly() {
        return String.format("%s%s%s%s%s",
                super.makeFileFriendly(), SEP, this.fromTime, SEP, this.toTime);
    }
}