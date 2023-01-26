package cbot.task;

import java.time.LocalDateTime;

public class Task implements Comparable<Task> {
    protected String desc;
    protected boolean isDone;
    
    public static final String DONE_TRUE = "X";
    public static final String DONE_FALSE = " ";
    public static final String SEP = " ;; ";
    public static final String TODO_SYMBOL = "T";
    
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }
    
    public Task(String desc) {
        this.desc = desc.trim();
        this.isDone = false;
    }
    
    public String getSymbol() {
        return TODO_SYMBOL;
    }
    
    public String getStatus() {
        return this.isDone ? DONE_TRUE : DONE_FALSE;
    }

    public boolean hasTime() {
        return false;
    }

    public LocalDateTime getTime() {
        return LocalDateTime.MIN;
    }
    
    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                getSymbol(), getStatus(), this.desc);
    }
    
    public String makeFileFriendly() {
        return String.format("%s%s%s%s%s",
                getSymbol(), SEP, getStatus(), SEP, this.desc);
    }
    
    public boolean yesDo() {
        if (this.isDone) {
            return false;
        } else {
            this.isDone = true;
            return true;
        }
    }
    
    public boolean noDo() {
        if (this.isDone) {
            this.isDone = false;
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public int compareTo(Task other) {
        if (this.getTime().isEqual(other.getTime())) {
            return this.desc.compareTo(other.desc);
        }
        
        return getTime().compareTo(other.getTime());
    }
}