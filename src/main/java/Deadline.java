public class Deadline extends Task {
    private String due;
    
    Deadline(String desc, boolean isDone, String due) {
        super(desc, isDone);
        this.due = due;
    }
    
    Deadline(String desc, String due) {
        super(desc);
        this.due = due;
    }
    
    @Override
    String getSymbol() {
        return "D";
    }
    
    @Override
    public String toString() {
        return String.format("%s (< %s)",
                super.toString(), this.due);
    }
    
    @Override
    String makeFileFriendly() {
        return String.format("%s%s%s",
                super.makeFileFriendly(), SEP, this.due);
    }
}