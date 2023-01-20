/*
taskList class HAS Tasks
Task attributes: index, isDone, taskDescription
Task methods: checkOff(int)
Tasklist
 */
public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toString() {
        if (isDone) {
            return "[X]" + taskName;
        } else {
            return "[ ]" + taskName;
        }
    }
}
