import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
    private static final DateTimeFormatter FORM = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    
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
        return String.format("[E][%s]  %s  (%s - %s)",
                getStatus(), this.desc, this.fromTime.format(FORM), this.toTime.format(FORM));
    }
}