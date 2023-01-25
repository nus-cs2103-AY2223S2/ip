import java.util.ArrayList;

public abstract class Task {
    private String name;
    private boolean isDone;
    public Task (String name, boolean isDone) {
        this.name = name.strip();
        this.isDone = isDone;
    }

    public abstract String getType();
    public abstract String getStatus();
    public abstract String getDescription();

    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsUndone() {
        this.isDone = false;
    }
    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    public String encode() {
        return getType() + "," + getStatus()+ "," + getDescription();
    }

    public static Task decode(String task) {
        String[] taskInfo = task.split(",");
        String type = taskInfo[0];
        boolean status = taskInfo[1] == "1";
        String name = taskInfo[2];
        if (type.equals("Deadline")) {
            String by = taskInfo[3];
            return new Deadline(name, by, status);
        } else if (type.equals("Event")) {
            String from = taskInfo[3];
            String to = taskInfo[4];
            return new Event(name, from, to, status);
        } else {
            return new Todo(name, status);
        }
    }
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.name;
    }
}
