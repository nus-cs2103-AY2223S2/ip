import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime due;
    
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
                super.toString(), TimeStuff.text(this.due));
    }

    @Override
    String makeFileFriendly() {
        return String.format("%s%s%s",
                super.makeFileFriendly(), SEP, this.due);
    }
}