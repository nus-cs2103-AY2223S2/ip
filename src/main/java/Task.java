public class Task {
    private String taskDescription;
    private int taskId;
    private boolean isDone;
    private static int numberOfTasks = 0;

    public Task(String taskName) {
        this.taskDescription = taskName;
        this.taskId = numberOfTasks + 1;
        this.isDone = false;
        numberOfTasks++;
    }

    public String getTaskStatus() {
        //Done tasks are marked with X
        return (isDone ? "[X]" : "[ ]");
    }

    //Mark current task as done
    public void markAsDone() {
        this.isDone = true;
    }

    //Mark current task as undone
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.taskId + ". " + this.getTaskStatus() + " " + this.taskDescription;
    }
}
