import java.util.Locale;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone=false;
    }

    @Override
    public String toString() {
        return "[" + (isDone? "X" : " ") + "] " + this.description;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }
}
