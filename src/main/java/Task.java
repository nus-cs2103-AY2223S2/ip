public class Task {
    private boolean isDone = false;
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void markStatus(boolean status) {
        this.isDone = status;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
