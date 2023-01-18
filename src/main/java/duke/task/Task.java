package duke.task;

public class Task {
    boolean done;
    private final String CHECKED = "[X]";
    private final String UNCHECKED = "[ ]";
    protected String title;
    protected String type;

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

    public boolean isMarked() {
        return this.done;
    }

    public String toData() {
        return this.type + " | " + (this.done ? "1" : "0") + " | " + this.title;
    }
    @Override
    public String toString() {
        return this.type + (done ? CHECKED : UNCHECKED) + " "  + this.title;
    }

}
