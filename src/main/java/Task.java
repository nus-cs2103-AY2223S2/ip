public class Task {
    private String done;
    private String name;

    public Task(String name) {
        this.done = " ";
        this.name = name;
    }

    public void setDone() {
        this.done = "X";
    }
    public void setUndone() {
        this.done = " ";
    }
    @Override
    public String toString() {
        return "[" + done + "] " + name;
    }
}
