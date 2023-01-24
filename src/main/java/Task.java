import java.time.LocalDateTime;

public class Task implements Comparable<Task> {
    protected String desc;
    protected boolean isDone;
    
    Task(String desc) {
        this.desc = desc.trim();
        this.isDone = false;
    }
    
    String getStatus() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }
    
    String getSymbol() {
        return "T";
    }
    
    LocalDateTime getTime() {
        return LocalDateTime.MIN;
    }
    
    boolean hasTime() {
        return false;
    }
    
    @Override
    public String toString() {
        return String.format("[T][%s]  %s",
                getStatus(), this.desc);
    }
    
    boolean yesDo() {
        if (this.isDone) {
            return false;
        } else {
            this.isDone = true;
            return true;
        }
    }
    
    boolean noDo() {
        if (this.isDone) {
            this.isDone = false;
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public int compareTo(Task other) {
        if (this.getTime().compareTo(other.getTime()) == 0) {
            return this.desc.compareTo(other.desc);
        }
        
        return getTime().compareTo(other.getTime());
    }
}