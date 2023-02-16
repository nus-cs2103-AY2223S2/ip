package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * A generic task object.
 */
public class Task {
    protected String desc;
    protected boolean isDone;
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    protected DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Creates a new Task object.
     * @param desc A description of the task.
     */
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

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), desc);
    }

    /**
     * Gets the task details to save in data/tasks.txt.
     * @return Task details.
     */
    public String getDetailsToSave() { // dummy
        return String.format("task %s", desc);
    }
    public void edit(String newDesc) {
        desc = newDesc;
    }
}
