public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String TaskName) {
        this.name = TaskName;
        this.isDone = false;
    }
    // gets name of task
    public String getName() {
        return this.name;
    }
    // gets status of task, mark done with 'X'
    public String getStatus() {
        return (isDone ? "X" : " ");
    }
    // marks task as done
    public void MarkDone()  {
        this.isDone = true;
    }
    // marks task as NOT done
    public void MarkNotDone() {
        this.isDone = false;
    }
    // properly formats Task as String
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + getName();
    }
}
