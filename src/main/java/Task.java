import java.util.ArrayList;

public class Task {
    public static ArrayList<Task> tasks =  new ArrayList<>(100);
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

    public String toSaveString() {
        return name + "$$$" + (isDone ? "T" : "F");
    }

    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + this.name;
    }
}