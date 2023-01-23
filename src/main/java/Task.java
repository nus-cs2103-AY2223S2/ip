public abstract class Task {
    String type;
    String taskName;
    boolean completed = false;

    public Task(String taskName, String type) {
        this.taskName = taskName;
        this.type = type;
    }

    public Task markDone() {
        this.completed = true;
        return this;
    } 
    public Task unmarkDone() {
        this.completed = false;
        return this;
    }

    public abstract String stringFields(); 

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + (this.completed ? "x" : " ") + "] " + this.taskName + this.stringFields();
    }
}