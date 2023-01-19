public class Task {
    protected String taskInfo;
    protected boolean isCompleted;

    public Task(String taskInfo) {
        this.taskInfo = taskInfo;
        this.isCompleted = false;
    }

    public String markAsDone() {
        this.isCompleted = true;
        return "     Nice! I've marked this task as done:\n       " + this.getTaskInfo();
    }

    public String markAsIncomplete() {
        this.isCompleted = false;
        return "     OK, I've marked this task as not done yet:\n       " + this.getTaskInfo();
    }

    public String getTaskInfo() {
        if (isCompleted) {
            return "[X] " + this.taskInfo;
        } else {
            return "[ ] " + this.taskInfo;
        }
    }
}
