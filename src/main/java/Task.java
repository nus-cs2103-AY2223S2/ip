public abstract class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        this.description = description;
    }



    public String getDescription() {
        return description;
    }

    boolean isDone() {
        return isDone;
    }

    void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toCsv() {
        return (isDone ? "1":"0") + "," + description;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "√" : " ") + "] " + description;
    }
}

