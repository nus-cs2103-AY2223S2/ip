package dudu.task;

import java.util.Objects;

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

    public boolean isDone() {
        return isDone;
    }
    public void markAsDone() {
        isDone = true;
    }
    public void markAsUndone() {
        isDone = false;
    }
    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    public String encode() {
        return getType() + "," + getStatus() + "," + getDescription();
    }

    public static Task decode(String task) {
        String[] taskInfo = task.split(",");
        String type = taskInfo[0];
        boolean status = taskInfo[1].equals("1");
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
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Task) {
            Task task = (Task) object;
            return isDone == task.isDone
                    &&
                    getDescription().equals(task.getDescription())
                    &&
                    getType().equals(task.getType());
        } else {
            return false;
        }

    }
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.name;
    }
}
