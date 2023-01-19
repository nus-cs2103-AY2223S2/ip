public class Task {
    private boolean isMarked;
    private String name;

    public Task(String name) {
        this.isMarked = false;
        this.name = name;
    }

    public void markDone() {
        isMarked = true;
    }

    public void unmark() {
        isMarked = false;
    }

    public String getStatusICon() {
        return (isMarked ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusICon() + "] " + name;
    }

}
