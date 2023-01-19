public class Task {

    private boolean isDone;
    private String name;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String done = isDone ? "x" : " ";
        String s = String.format("[%s] %s", done, this.name);
        return s;
    }
}
