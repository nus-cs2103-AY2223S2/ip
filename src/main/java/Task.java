public class Task {
    private String name;    // title of the task
    private boolean isDone;   // is the task completed?

    // task is by default uncompleted.
    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    // 2 commands to change completeness of task
    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    // returns done status
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    // returns name
    public String getName() {
        return name;
    }
}
