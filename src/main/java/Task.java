public abstract class Task {
    private String Name;
    private boolean isDone;

    public Task(String taskName) {
        this.Name = taskName;
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if(this.isDone) {
            return "[X]" + this.Name;
        }
        else {
            return "[ ]" + this.Name;
        }
    }
}