public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected void mark() {
        isDone = true;
    }

    protected void unmark() {
        isDone = false;
    }

    public String getStatusIndicator() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    @Override
    public String toString() {
        return "Todo/" + getTaskDescription();
    }

    protected String getTaskDescription() {
        return getStatusIndicator() + " " + description;
    }
}
