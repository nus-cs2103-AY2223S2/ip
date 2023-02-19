package jane.task;

/**
 * Making a task
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected int num;
    /**
     * @param num is task ID
     * @param description is task description (what to do)
     */
    public Task(int num, String description) {
        this.num = num;
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void changeState(boolean stat) {
        this.isDone = stat;
    }
    public String getDesc() {
        return this.description;
    }
    public void changeNum() {
        this.num -= 1;
    }
    public String save() {
        return this.toString();
    }
    @Override
    public String toString() {
        return String.format("%d. [%s](%s)", this.num, this.getStatusIcon(), this.description);
    }
}


