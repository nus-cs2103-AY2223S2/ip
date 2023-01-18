package types;

public class Task {
    private final String name;
    private boolean done;

    private Task(String s) {
        this.name = s;
        this.done = false;
    }

    public static Task ofName(String s) {
        return new Task(s);
    }

    public String getName() {
        return name;
    }

    public void setDone() {
        this.done = true;
    }

    public void setUndone() {
        this.done = false;
    }

    public boolean getDone() {
        return this.done;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
