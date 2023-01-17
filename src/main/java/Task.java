public class Task {
    private boolean done;
    private String description;

    public Task(String description) {
        this.done = false;
        this.description = description;
    }

    public void setDone() {
        this.done = true;
    }

    public void setNotDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String checkbox = "[ ] ";
        if (done) checkbox = "[X] ";
        return checkbox + description;
    }
}
