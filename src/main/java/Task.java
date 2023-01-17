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
    public boolean markAsDone() {
        //Only mark as done if task is not done
        if (!this.isDone) {
            this.isDone = true;
            return true;
        }
        return false;
    }

    //Mark current task as undone
    public boolean markAsUndone() {
        //Only remark as not done if task is done
        if (this.isDone) {
            this.isDone = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.taskId + ". " + this.getTaskStatus() + " " + this.taskDescription;
    }
}
