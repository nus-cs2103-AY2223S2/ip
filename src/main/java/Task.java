import java.util.ArrayList;

public class Task {
    public static ArrayList<Task> taskList = new ArrayList<>();
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskList.add(this);
    }

    public static Task getTask(int i) {
        return Task.taskList.get(i);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

}
