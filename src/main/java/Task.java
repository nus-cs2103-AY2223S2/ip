public class Task {

    private boolean isDone;
    private String name;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "x" : " ");
    }

    @Override
    public String toString() {
        String s = String.format("[%s] %s", this.getStatusIcon(), this.name);
        return s;
    }
}
