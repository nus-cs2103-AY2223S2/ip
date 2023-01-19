public class Task {

    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    public String getIcon() {
        return "T";
    }

    @Override
    public String toString() {
        return description;
    }

    public void setDone(boolean val) {
        this.done = val;
    }

}
