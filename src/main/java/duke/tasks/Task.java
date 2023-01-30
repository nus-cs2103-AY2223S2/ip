package duke.tasks;

public class Task {
    private String description;
    private boolean status;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public String getDescription() {
        return this.description;
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

    public String getDataString() {
        if(this.isDone()) {
            return "1 | " + this.getDescription();
        } else {
            return "0 | " + this.getDescription();
        }
    }

    @Override
    public String toString() {
        if(this.isDone()) {
            return "[X] " + this.getDescription();
        } else {
            return "[ ] " + this.getDescription();
        }
    }
}