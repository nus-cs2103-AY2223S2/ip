public class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }
    public boolean getDone() {
        return isDone;
    }
    public boolean markDone() {
        this.isDone = true;
        return true;
    }
    public boolean unmarkDone() {
        this.isDone = false;
        return false;
    }

    @Override
    public String toString() {
        String box;
        if (isDone) {
            box = "[X]";
        } else {
            box = "[ ]";
        }
        return"[T]" + box + this.getName();
    }
}
