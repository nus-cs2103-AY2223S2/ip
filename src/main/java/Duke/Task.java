package Duke;

/**
 * Abstract class representing a task.
 * @author Bryan Juniano
 */

public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if(this.isDone) {
            return "[X] | " + this.name;
        }
        else {
            return "[ ] | " + this.name;
        }
    }
}