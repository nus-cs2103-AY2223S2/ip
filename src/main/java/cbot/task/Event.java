package cbot.task;

import cbot.util.TimeStuff;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
    
    public Event(String desc, boolean isDone, LocalDateTime fromTime, LocalDateTime toTime) {
        super(desc, isDone);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
    
    public Event(String desc, LocalDateTime fromTime, LocalDateTime toTime) {
        super(desc);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
    
    @Override
    public String getSymbol() {
        return "E";
    }
    
    @Override
    public boolean hasTime() {
        return true;
    }
    
    @Override
    public LocalDateTime getTime() {
        return this.fromTime;
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s - %s)",
                super.toString(), TimeStuff.text(this.fromTime), TimeStuff.text(this.toTime));
    }
    
    @Override
    public String makeFileFriendly() {
        return String.format("%s%s%s%s%s",
                super.makeFileFriendly(), SEP, this.fromTime, SEP, this.toTime);
    }
}