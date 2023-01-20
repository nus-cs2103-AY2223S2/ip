package dukes.engine;

public class Task {
    protected String taskName;
    protected boolean isDone;
    protected String tag;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.tag = "";
    }

    void setDone() {
        this.isDone = true;
    }

    void setUnDone() {
        this.isDone = false;
    }

    public String getTag() { return this.tag; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (this.isDone) {
            sb.append("X");
        } else {
            sb.append(" ");
        }
        sb.append("] ").append(this.taskName);
        return sb.toString();
    }
}
