import java.io.Serializable;

public abstract class Task implements Serializable {
    protected final String desc;
    protected boolean isDone;

    Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return this.isDone ? "[X]" : "[]";
    }

    protected abstract String getType();

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
