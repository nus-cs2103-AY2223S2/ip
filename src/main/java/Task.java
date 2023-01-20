import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM '('EEE')' - hh:mma");
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public abstract String saveString();

    @Override
    public String toString() {
        String done = isDone ? "X" : " ";
        return "[" + done + "] " + description;
    }
}