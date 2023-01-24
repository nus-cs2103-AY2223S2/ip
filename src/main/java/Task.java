public abstract class Task {
    protected String description;
    protected boolean isDone = false;
    private String eventSymbol;

    public Task(String description, String eventSymbol) {
        this.description = description;
        this.eventSymbol = eventSymbol;
    }

    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    public void setDone(boolean value) {
        isDone = value;
    }

    @Override
    public String toString() {
        return "[" + eventSymbol + "]" + "[" + getStatusIcon() + "] " + description;
    }

    public String toSaveString(Storage storage) {
        return eventSymbol + "|" + isDone + "|" + description;
    }

}
