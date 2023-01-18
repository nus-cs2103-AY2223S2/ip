public class Task {
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

    public void printTask() {

    }
}
