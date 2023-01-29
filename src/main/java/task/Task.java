package task;
public class Task {
    private String taskName;
    private boolean tag;

    public Task(String taskName) {
        this.taskName = taskName;
        this.tag = false;
    }
 
    public void mark() {
        this.tag = true;
    }

    public void unmark() {
        this.tag = false;
    }

    protected String tag() {
        if(this.tag) {
            return "[X]";
        }
        else {
            return "[]";
        }
    }

    @Override
    public String toString() {
        return this.tag() + " |" + taskName + "|";
    }
}
