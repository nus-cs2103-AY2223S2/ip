package duke.task;
public abstract class Task {
    private boolean isDone;
    private String task;

    public Task(String title) {
        this.task = title;
    }

    public void markDone(boolean needPrint) {
        this.isDone = true;
        if (needPrint) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.toString());
        }
    }

    public void unmark(boolean needPrint) {
        this.isDone = false;
        if (needPrint) {
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.toString());
        }
    }

    public String getTask() {
        return this.task;
    }

    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "[" + mark + "] " + this.task;
    }

    public String toTxtString() {
        String mark2 = this.isDone ? "1" : "0";
        return "|" + mark2 + "|" + this.task;
    }
}