public class Task {
    boolean done;
    String checked = "[X]";
    String unchecked = "[ ]";
    String title;

    public Task(String title) {
        this.title = title;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        return (done ? checked : unchecked) + " "  + this.title;
    }

}
