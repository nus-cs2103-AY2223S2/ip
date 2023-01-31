package task;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class Task {
    private String description;
    DateFormat readFormat = new SimpleDateFormat( "dd/MM/yyyy HHmm");
    DateFormat writeFormat = new SimpleDateFormat( "E, MMM dd yyyy, h:mm aa");
    private boolean isComplete;
    public Task(String description) {
        this.description = description;
        this.isComplete  = false;
    }
    public String getDescription() {
        return this.description;
    }
    public boolean isComplete() {
        return this.isComplete;
    }
    public void mark() {
        this.isComplete = true;
    }
    public void unmark() {
        this.isComplete = false;
    }
    public abstract String save();
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[] ");
        sb.append(this.description);
        if (this.isComplete) {
            sb.insert(1, "X");
        }
        return sb.toString();
    }
}
