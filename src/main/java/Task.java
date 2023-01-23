public class Task {
    protected String desc;
    protected boolean isDone;

    static final String SEP = " ;; ";
    static final String DONE_TRUE = "X";
    static final String DONE_FALSE = " ";
    
    Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }
    
    Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }
    
    String getSep() {
        return SEP;
    }
    
    String getSymbol() {
        return "T";
    }
    
    String getStatus() {
        return this.isDone ? DONE_TRUE : DONE_FALSE;
    }
    
    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                getSymbol(), getStatus(), this.desc);
    }
    
    String makeFileFriendly() {
        return String.format("%s%s%s%s%s",
                getSymbol(), SEP, getStatus(), SEP, this.desc);
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
}