public class Deadline extends Task {
    private String due;
    
    Deadline(String desc, String due) {
        super(desc);
        this.due = due;
    }
    
    @Override
    public String toString() {
        return String.format("[D][%s]  %s  (< %s)",
                getStatus(), this.desc, this.due);
    }
}