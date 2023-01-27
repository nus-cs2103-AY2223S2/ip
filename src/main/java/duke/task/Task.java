package duke.task;

public class Task {
    private String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean nameContainsKeyword(String keyword) {
        return name.contains(keyword);
    }

    public String toSaveString() {
        return name + "$$$" + (isDone ? "T" : "F");
    }

    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + this.name;
    }
}