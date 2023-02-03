package duke; 

public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getTask() {
        return this.name;
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

    @Override
    public String toString() {
        return this.done ? "[X] " + this.name : "[ ] " + this.name; 
    }

    public String getName() {
        return null;
    }
}