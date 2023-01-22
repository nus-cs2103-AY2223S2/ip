package duke;
abstract class Task {
    String type;
    String taskName;
    boolean completed = false;

    public Task(String taskName, String type) {
        this.taskName = taskName;
        this.type = type;
    }

    public void markDone() {
        this.completed = true;
    } 
    public void unmarkDone() {
        this.completed = false;
    }

    public abstract String stringFields(); 

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + (this.completed ? "x" : " ") + "] " + this.taskName + this.stringFields();
    }
}