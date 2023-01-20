public abstract class Task {
    private boolean isDone = false;
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void setDone(boolean status) {
        this.isDone = status;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public abstract String getFileRepresentation();

    protected String getName() {
        return this.name;
    };

    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.name;
    }
}
