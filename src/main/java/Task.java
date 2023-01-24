import java.io.Serializable;

public abstract class Task implements Serializable {
    protected final String desc;
    protected boolean isDone;

    Task(String desc) throws TaskCreationException {
        if (desc.equals("")) {
            throw new TaskCreationException("Description of todo cannot be empty");
        }
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
