package duke.task;

import java.time.format.DateTimeFormatter;

public class Task {
    protected String desc;
    protected boolean isDone;
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    protected DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }
    public String getDesc() {
        return desc;
    }

    public boolean isDone() {
        return isDone;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), desc);
    }

    public String getDetailsToSave() { // dummy
        return String.format("task %s", desc);
    }
}
