public abstract class Task {
    protected String taskInfo;
    protected boolean isCompleted;

    public Task(String taskInfo) {
        this.taskInfo = taskInfo;
        this.isCompleted = false;
    }

    public String markAsDone() {
        this.isCompleted = true;
        return "     Nice! I've marked this task as done:\n       " + this.getTaskInfoStatus();
    }

    public String markAsIncomplete() {
        this.isCompleted = false;
        return "     Alright, I've marked this task as not done yet:\n       " + this.getTaskInfoStatus();
    }

    public String getTaskInfoStatus() {
        if (isCompleted) {
            return "[X] " + this.taskInfo;
        } else {
            return "[ ] " + this.taskInfo;
        }
    }

    public abstract String getTaskInfo();
}
