public class Task {

    private final String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }
    
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "[" + mark + "] " + this.description;
    }
}
