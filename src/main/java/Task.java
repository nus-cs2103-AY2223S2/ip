public class Task {
    boolean done;
    String checked = "[X]";
    String unchecked = "[ ]";
    String title;
    String type;

    public Task(String title) {
        this.title = title;
        this.done = false;
        this.type = "";
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        return this.type + (done ? checked : unchecked) + " "  + this.title;
    }

}
