package task;

public class Task {
    private String name;
    private Boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public Task(String name, Boolean isDone) {
        this.name = name;
        this.completed = isDone;
    }

    public void check() {
        this.completed = true;
    }

    public void unCheck() {
        this.completed = false;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
