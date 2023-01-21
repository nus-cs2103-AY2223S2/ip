public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void changeCompletion() {
        this.isDone = !this.isDone;
    }

    public String toString() {
        String var10000 = this.getStatusIcon();
        return "[" + var10000 + "] " + this.description;
    }
}
