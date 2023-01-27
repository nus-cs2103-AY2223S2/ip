package duke.Tasks;

public class Task {
    protected String description;
    public boolean isDone;

    public static int taskNum;
    public boolean isExit;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
        taskNum++;
        this.isExit = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.description;
    }

    public String file() {
        String status = isDone? "1" : "0";
        return " | " + status + " | " + getDescription();
    }
}
