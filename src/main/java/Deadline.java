import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime due;
    private static final DateTimeFormatter FORM = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    
    Deadline(String desc, LocalDateTime due) {
        super(desc);
        this.due = due;
    }
    
    @Override
    String getSymbol() {
        return "D";
    }
    
    @Override
    boolean hasTime() {
        return true;
    }
    
    @Override
    LocalDateTime getTime() {
        return this.due;
    }
    
    @Override
    public String toString() {
        return String.format("[D][%s]  %s  (< %s)",
                getStatus(), this.desc, this.due.format(FORM));
    }
}