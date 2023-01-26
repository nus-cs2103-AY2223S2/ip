public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
    public String toStoreString() {
        String isDoneIndicator = isDone ? "1 |" : "0 |";
        return isDoneIndicator + this.description;
    }


}


