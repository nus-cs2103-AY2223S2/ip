import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime due;
    private static final DateTimeFormatter FORM = DateTimeFormatter.ofPattern("dd/MM/yy, HHmm");
    
    Deadline(String desc, boolean isDone, LocalDateTime due) {
        super(desc, isDone);
        this.due = due;
    }
    
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
        return String.format("%s (< %s)",
                super.toString(), this.due.format(FORM));
    }

    @Override
    String makeFileFriendly() {
        return String.format("%s%s%s",
                super.makeFileFriendly(), SEP, this.due);
    }
}