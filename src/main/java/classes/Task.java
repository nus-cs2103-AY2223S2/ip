package classes;

public abstract class Task {
    protected String taskInfo;
    protected boolean hasCompleted;

    public Task(String taskInfo) {
        this.taskInfo = taskInfo;
        this.hasCompleted = false;
    }

    public String markAsDone() {
        this.hasCompleted = true;
        return "     Nice! I've marked this task as done:\n       " + this.getTaskInfoStatus();
    }

    public String markAsIncomplete() {
        this.hasCompleted = false;
        return "     Alright, I've marked this task as not done yet:\n       " + this.getTaskInfoStatus();
    }

    public String getTaskInfoStatus() {
        if (hasCompleted) {
            return "[X] " + this.taskInfo;
        } else {
            return "[ ] " + this.taskInfo;
        }
    }

    public abstract String getTaskInfo();
}
