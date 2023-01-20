package tasks;

public class Task {
    private String description;
    private boolean status; // false meaning not done yet
    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public void markDone() {
        this.status = true;
    }

    public void unmarkDone() {
        this.status = false;
    }

    public boolean isDone() {
        return this.status;
    }

    @Override
    public String toString() {
        if(this.isDone()) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}