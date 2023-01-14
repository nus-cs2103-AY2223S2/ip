public class Task {
    protected String taskName;
    protected boolean completed = false;
    protected String taskType = "T";
    protected static int numTasks = 0;

    public Task(String taskName) {
        this.taskName = taskName;
        numTasks++;
    }

    public void setCompletion(boolean completion) {
        this.completed = completion;
        if (completion) {
            System.out.println("    Nice! I've marked this task as done:\n" + "   " + this);
        } else {
            System.out.println("    OK, I've marked this task as not done yet:\n" + "   " + this);
        }
    }

    public String displayType() {
        return String.format("[%s]", this.taskType);
    }

    public String displayMark() {
        if (this.completed) {
            return "[X]";
        }
        return "[ ]";
    }

    @Override
    public String toString() {
        return String.format("  %s %s", displayMark(), this.taskName);
    }
}
