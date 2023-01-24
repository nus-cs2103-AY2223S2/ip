import java.time.format.DateTimeFormatter;
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    protected DateTimeFormatter outputDateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    abstract public String toEncodedString();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}
