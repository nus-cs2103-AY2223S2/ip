public class Task {
    private boolean isMarked;
    private String name;

    public Task(String name,boolean isMarked) {
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
    public String getName() {
        return this.name;
    }

}
