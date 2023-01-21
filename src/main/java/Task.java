public abstract class TaskNew {
    private String description;

    private boolean isDone;

    public String toString() {
        return description;
    }

    public void mark() { this.isDone = true; }

    public void unmark() {this.isDone = false; }

    
}
