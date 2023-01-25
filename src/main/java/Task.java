public abstract class Task {
    protected boolean completed;
    protected String taskName;

    public Task(String taskname) {
        this.taskName = taskname;
        this.completed = false;
    }

    public void markAsDone() {
        this.completed = true;
    }

    public void markAsUndone() {
        this.completed = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean status) { this.completed = status; }

    public String toFile() {
        if (this.completed) {
            return String.format("1 | %s", this.taskName);
        } else {
            return String.format("0 | %s", this.taskName);
        }
    }

    @Override
    public String toString() {
        String s;
        if (this.completed) {
            s = "[X] " + this.taskName;
        } else {
            s = "[ ] " + this.taskName;
        }
        return s;
    }

}
