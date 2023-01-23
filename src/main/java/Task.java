public class Task {
    protected String name;
    protected boolean isMarked;

    public Task(String name) {
        this.name = name;
        this.isMarked = false;
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }
    public String toString() {
        if (this.isMarked == true) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
