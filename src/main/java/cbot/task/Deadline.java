package cbot.task;

import cbot.util.TimeStuff;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime due;
    
    public static final String DEADLINE_SYMBOL = "D";
    
    public Deadline(String desc, boolean isDone, LocalDateTime due) {
        super(desc, isDone);
        this.due = due;
    }
    
    public Deadline(String desc, LocalDateTime due) {
        super(desc);
        this.due = due;
    }
    
    @Override
    public String getSymbol() {
        return DEADLINE_SYMBOL;
    }
    
    @Override
    public boolean hasTime() {
        return true;
    }
    
    @Override
    public LocalDateTime getTime() {
        return this.due;
    }
    
    @Override
    public String toString() {
        return String.format("%s (< %s)",
                super.toString(), TimeStuff.text(this.due));
    }

    @Override
    public String makeFileFriendly() {
        return String.format("%s%s%s",
                super.makeFileFriendly(), SEP, this.due);
    }
}